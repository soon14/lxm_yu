package com.lxm.ss.kuaisan.ui.trailer_infor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;

import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/12/1.
 */

public class TrailerInforListAdapter extends ArrayAdapter<TrailerInfor> {

    private LayoutInflater layoutInflater ;

    public TrailerInforListAdapter(@NonNull Context context, int resource, @NonNull List<TrailerInfor> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHoder viewHoder ;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.trail_infor_item,null);
            viewHoder = new ViewHoder();
            viewHoder.txtTitle = (TextView) convertView.findViewById(R.id.trailer_infor_item_txt_title);
            viewHoder.txtContent = (TextView) convertView.findViewById(R.id.trailer_infor_item_txt_content);
            viewHoder.txtTime = (TextView) convertView.findViewById(R.id.trailer_infor_item_txt_time);

            convertView.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        TrailerInfor item = getItem(position);
        String title = item.getTitle();
        String content = item.getContent();
        String time = item.getTime();

        viewHoder.txtTitle.setText(StringUtils.isNotBlank(title) == true ? title:"");
        viewHoder.txtContent.setText(StringUtils.isNotBlank(content) == true ?content:"");
        viewHoder.txtTime.setText(StringUtils.isNotBlank(time) == true ?time:"");


        return convertView;
    }


    class ViewHoder {

        TextView txtTitle ;
        TextView txtContent ;
        TextView txtTime ;

    }
}
