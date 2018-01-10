package com.zzc.somedesigns.loginandperson.login;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.transition.ChangeBounds;
import android.support.transition.Scene;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;

import com.zzc.somedesigns.R;
import com.zzc.somedesigns.databinding.SceneLoginAboveBinding;
import com.zzc.somedesigns.databinding.SceneLoginBelowBinding;
import com.zzc.somedesigns.loginandperson.person.PersonActivity;

/**
 * @auth zzc
 * @date 2017/12/13
 * @desc ${desc}
 */
public class LoginDialog extends DialogFragment {
    private static final String TAG = LoginDialog.class.toString();
    private static final String KEY = "key";
    private TransitionManager mTransitionManager;
    private Scene mAbove;
    private Scene mBelow;
    private boolean isLogin;
    private View.OnClickListener changeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTransitionManager.transitionTo(!isLogin ? mAbove : mBelow);
            isLogin = !isLogin;
        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(mAboveBinding.vLogin.edtLoginPsd.getText().toString()) &&
                    !TextUtils.isEmpty(mAboveBinding.vLogin.edtLoginEmail.getText().toString())) {
                mAboveBinding.vLogin.tvLoginIn.setVisibility(View.VISIBLE);
            } else {
                mAboveBinding.vLogin.tvLoginIn.setVisibility(View.GONE);
            }
        }
    };
    private SceneLoginAboveBinding mAboveBinding;
    private SceneLoginBelowBinding mBelowBinding;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        isLogin = getArguments().getBoolean(KEY);
        Dialog dialog = new Dialog(getActivity(), R.style.TransparentDialog);
        dialog.getWindow().setWindowAnimations(R.style.dialog_bottom_slide);
//---------------------------------------------初始化View------------------------------------------
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_login, null);
        FrameLayout sceneParent = (FrameLayout) rootView.findViewById(R.id.dialog_scene);
        View aboveView = LayoutInflater.from(getContext()).inflate(R.layout.scene_login_above, sceneParent, false);
        View belowView = LayoutInflater.from(getContext()).inflate(R.layout.scene_login_below, sceneParent, false);
        mAboveBinding = DataBindingUtil.bind(aboveView);
        mBelowBinding = DataBindingUtil.bind(belowView);
        mAbove = new Scene(sceneParent, aboveView);
        mBelow = new Scene(sceneParent, belowView);
        mTransitionManager = new TransitionManager();
        Transition transition = new ChangeBounds();
        transition.setDuration(800);
        transition.setInterpolator(new BounceInterpolator());
        mTransitionManager.setTransition(mAbove, transition);
        mTransitionManager.setTransition(mBelow, transition);
        mTransitionManager.transitionTo(!isLogin ? mBelow : mAbove);
        dialog.setContentView(rootView);
//---------------------------------------------设置监听------------------------------------------
        mAboveBinding.vRegister.vChange.setOnClickListener(changeListener);
        mBelowBinding.vLogin.vChange.setOnClickListener(changeListener);
        mAboveBinding.vLogin.edtLoginEmail.addTextChangedListener(mTextWatcher);
        mAboveBinding.vLogin.edtLoginPsd.addTextChangedListener(mTextWatcher);
        mAboveBinding.vLogin.tvLoginIn.setOnClickListener(v -> {
            String email = mAboveBinding.vLogin.edtLoginEmail.getText().toString();
            String psd = mAboveBinding.vLogin.edtLoginPsd.getText().toString();
            PersonActivity.start(getActivity(), email, psd);
            dismiss();
        });
        return dialog;
    }

    public static LoginDialog newInstance(boolean login) {
        LoginDialog loginDialog = new LoginDialog();
        Bundle args = new Bundle();
        args.putBoolean(KEY, login);
        loginDialog.setArguments(args);
        return loginDialog;
    }

    public void show(FragmentManager manager) {
        this.show(manager, TAG);
    }
}
