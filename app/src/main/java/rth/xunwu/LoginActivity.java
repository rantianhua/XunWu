package rth.xunwu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import rth.fragment.LoginFragment;

/**
 * Created by Rth on 2015/3/12.
 */
public class LoginActivity  extends FragmentActivity {

    private FragmentManager fm = getSupportFragmentManager();;
    android.support.v4.app.FragmentTransaction transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(transaction == null) {
            transaction = fm.beginTransaction();
        }
        transaction.add(R.id.ll_container_login,new LoginFragment());
        finshTranscation();
    }

    private void finshTranscation() {
        if (transaction != null) {
            transaction.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (transaction != null) {
            transaction = null;
        }
    }
}
