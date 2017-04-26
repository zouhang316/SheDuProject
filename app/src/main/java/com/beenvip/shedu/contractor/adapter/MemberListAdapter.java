package com.beenvip.shedu.contractor.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneNumberUtils;
import android.view.View;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.adaper.BaseListViewAdapter;
import com.beenvip.shedu.base.adaper.BaseListViewViewHolder;
import com.beenvip.shedu.httputils.PermissionMgr;
import com.beenvip.shedu.utils.MyContacts;

import java.util.List;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MemberListAdapter extends BaseListViewAdapter<MyContacts> {

    public MemberListAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_member;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, final MyContacts myContacts, int position) {
        holder.setText(R.id.name,myContacts.getName());
        holder.setText(R.id.phoneNumber,myContacts.getPhoneNumber());
        holder.setImageBitmap(R.id.head,myContacts.getHead());
        holder.setOnClickListener(R.id.message, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送短信
                getSendMSMPermission(mContext);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String phoneNumber=myContacts.getPhoneNumber();
                if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
                    intent.putExtra("sms_body", "");
                    mContext.startActivity(intent);
                }
            }
        });
        holder.setOnClickListener(R.id.callphone, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拨打电话
                getCallPhonePermission(mContext);
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + myContacts.getPhoneNumber());
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });

    }
    private void getSendMSMPermission(Context context){
        PermissionMgr.grantPermission((Activity) context,new String[]{
                Manifest.permission.SEND_SMS
        });
    }
    private void getCallPhonePermission(Context context){
        PermissionMgr.grantPermission((Activity) context,new String[]{
                Manifest.permission.CALL_PHONE
        });
    }


}
