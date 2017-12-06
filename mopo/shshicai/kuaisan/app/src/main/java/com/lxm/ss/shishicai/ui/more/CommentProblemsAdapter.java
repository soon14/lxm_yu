package com.lxm.ss.shishicai.ui.more;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lxm.ss.shishicai.R;

import java.util.List;

/**
 * Created by lxm on 2017/12/4.
 */

public class CommentProblemsAdapter extends ArrayAdapter<CommenProblems> {

    private LayoutInflater layoutInflater ;

    public CommentProblemsAdapter(@NonNull Context context, int resource, @NonNull List<CommenProblems> objects) {
        super(context, resource, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder ;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.comment_problems_item,null);
            viewHolder = new ViewHolder();
            viewHolder.mTxtTitle = (TextView) convertView.findViewById(R.id.commen_problems_item_txt_title);
            viewHolder.mTxtContent = (TextView) convertView.findViewById(R.id.commen_problems_item_txt_content);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommenProblems item = getItem(position);

        if (item.isTitle()) {
            viewHolder.mTxtTitle.setVisibility(View.VISIBLE);
            viewHolder.mTxtContent.setVisibility(View.GONE);
            viewHolder.mTxtTitle.setText(item.getProblems());
        }else {
            viewHolder.mTxtContent.setVisibility(View.VISIBLE);
            viewHolder.mTxtTitle.setVisibility(View.GONE);
            viewHolder.mTxtContent.setText(item.getProblems());
        }
        return convertView;
    }

    class ViewHolder {

        TextView mTxtTitle;
        TextView mTxtContent ;

    }
}
