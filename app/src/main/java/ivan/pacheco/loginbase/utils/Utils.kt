package ivan.pacheco.loginbase.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import ivan.pacheco.loginbase.R
import ivan.pacheco.loginbase.view.CheckEmailActivity
import ivan.pacheco.loginbase.view.LoginActivity
import ivan.pacheco.loginbase.view.MainActivity
import ivan.pacheco.loginbase.view.RecoveryPasswordActivity
import ivan.pacheco.loginbase.view.RegisterActivity

object Utils {

    /**
     * Ocultar el teclado digital del móvil
     */
    fun hideKeyboard(view: View, context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Navegar a la pantalla de Main
     */
    fun goToMain(activity: Activity) {
        activity.finish()
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * Navegar a la pantalla de Login
     */
    fun goToLogin(activity: Activity) {
        activity.finish()
        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * Navegar a la pantalla de Registro
     */
    fun goToRegister(activity: Activity) {
        activity.finish()
        val intent = Intent(activity, RegisterActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * Navegar a la pantalla de CheckEmail
     */
    fun goToCheckEmail(activity: Activity) {
        activity.finish()
        val intent = Intent(activity, CheckEmailActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * Navegar a la pantalla de RecoveryPassword
     */
    fun goToRecoveryPassword(activity: Activity) {
        activity.finish()
        val intent = Intent(activity, RecoveryPasswordActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * Alerta Error personalizado
     */
    fun customAlertError(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.alertError))
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Alerta Warning personalizado
     */
    fun customAlertWarning(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.alertWarning))
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Toast Long personalizado
     */
    fun customToastLong(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG)
            .show()
    }

    /**
     * Toast Short personalizado
     */
    fun customToastShort(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

}