package rth.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/12.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.et_password)
    EditText etpassword;
    @InjectView(R.id.cb_memory_me)
    CheckBox memoryMe;
    @InjectView(R.id.tv_forget_password)
    TextView tvForget;
   @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login,null);
        initView(v);
        return  v;
    }

    private void initView(View v) {
        ButterKnife.inject(this,v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
