package com.yiwo.friendscometogether.sp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SpImp {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SpImp(Context context) {
        sp = context.getSharedPreferences(SpPublic.SP_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
    }
    public String getUID() {
        return sp.getString(SpPublic.UID, "");
    }

    public void setUID(String UID) {
        editor.putString(SpPublic.UID, UID).toString();
        editor.commit();
    }



    //长整形

    public Long getLastlogin() {
        return sp.getLong(SpPublic.LASTLOGIN, 0);
    }

    public void setLastlogin(Long lastlogin) {
        editor.putLong(SpPublic.LASTLOGIN, lastlogin);
        editor.commit();
    }
    //字符串类型
    public String getACCOUNTNO() {
        return sp.getString(SpPublic.ACCOUNTNO, "");
    }
    public void setTOKEN(String token){
        editor.putString(SpPublic.TOKEN,token).toString();
        editor.commit();
    }

    public void setACCOUNTNO(String accountno) {
        editor.putString(SpPublic.ACCOUNTNO, accountno).toString();
        editor.commit();
    }
    public String getTOKEN(){
        return sp.getString(SpPublic.TOKEN,"");
    }
    //字符串类型
    public String getPASSWORD() {
        return sp.getString(SpPublic.PASSWORD, "");
    }

    public void setPASSWORD(String password) {
        editor.putString(SpPublic.PASSWORD, password).toString();
        editor.commit();
    }
    //字符串类型
    public String getUSERINFOFLAG() {
        return sp.getString(SpPublic.USERINFOFLAG, "");
    }

    public void setUSERINFOFLAG(String userinfoflag) {
        editor.putString(SpPublic.USERINFOFLAG, userinfoflag).toString();
        editor.commit();
    }

    public void setSTATE(boolean state){
        editor.putBoolean(SpPublic.STATE,state);
        editor.commit();
    }
    public boolean getSTATE(){
        return sp.getBoolean(SpPublic.STATE,false);
    }
}
