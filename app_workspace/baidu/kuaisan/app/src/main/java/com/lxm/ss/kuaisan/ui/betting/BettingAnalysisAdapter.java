package com.lxm.ss.shishicai.ui.betting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.Utils.ImageUtils;
import com.lxm.ss.shishicai.ui.betting.model.BettingAnalysisInfor;

import java.util.List;

import club.fromfactory.fresco.view.FrescoImageView;

/**
 * Created by lxm on 2017/11/28.
 */

public class BettingAnalysisAdapter extends ArrayAdapter<BettingAnalysisInfor> {

    private LayoutInflater layoutInflater ;

    public BettingAnalysisAdapter(@NonNull Context context, int resource, @NonNull List<BettingAnalysisInfor> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView =  layoutInflater.inflate(R.layout.betting_analysis_item,null);

            viewHolder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            viewHolder.frescoImageView = (FrescoImageView) convertView.findViewById(R.id.trailer_infor_item_img);
            viewHolder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder  = (ViewHolder) convertView.getTag();
        }
        BettingAnalysisInfor item = getItem(position);

        viewHolder.txt_title.setText(item.getTitle());
        viewHolder.txt_content.setText(item.getContent());
        ImageUtils.loadImage(viewHolder.frescoImageView,item.getImgUrl(),false,R.mipmap.icon_logo);

        return convertView;
    }


    class ViewHolder {

        FrescoImageView frescoImageView ;
        TextView txt_title ;
        TextView txt_content ;

    }
}
