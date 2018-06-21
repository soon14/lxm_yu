package com.lxm.ss.kuaisan.ui.lottery_infor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.model.LotterInfor;

import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/11/28.
 */

public class NewLotterAdapter extends ArrayAdapter<LotterInfor> {

    private LayoutInflater layoutInflater ;

    private int res ;

    public NewLotterAdapter(@NonNull Context context, int resource, @NonNull List<LotterInfor> objects) {
        super(context, resource, objects);
        this.res = resource ;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView =  layoutInflater.inflate(res,null);
            viewHolder.txt_type = (TextView) convertView.findViewById(R.id.txt_type);
            viewHolder.txt_time = (TextView) convertView.findViewById(R.id.txt_time);
            viewHolder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder  = (ViewHolder) convertView.getTag();
        }

        LotterInfor item = getItem(position);
        String gameEn = item.getGameEn();
        gameEn = StringUtils.isNotBlank(FFApplication.getInstance().mapLotterInfor.get(gameEn)) == true ?FFApplication.getInstance().mapLotterInfor.get(gameEn) :gameEn;
        String awardTime = item.getAwardTime();
        String awardNo = item.getAwardNo();
        String periodName = item.getPeriodName();

        viewHolder.txt_type.setText(gameEn);
        viewHolder.txt_time.setText("第"+periodName +"期 " +awardTime);
        viewHolder.txt_content.setText(awardNo);
        return convertView;
    }


    class ViewHolder {

        TextView txt_type ;
        TextView txt_time ;
        TextView txt_content ;

    }
}
