package com.example.mao.BBCLearningEnglish;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mao.BBCLearningEnglish.data.BBCContentContract;
import com.squareup.picasso.Picasso;

/**
 * Created by MAO on 7/17/2017.
 */

public class BBCContentAdapter extends
        RecyclerView.Adapter<BBCContentAdapter.BBCContentAdapterViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    private OnListItemClickListener mOnClickListener;

    public BBCContentAdapter(Context context, OnListItemClickListener listener) {
        mContext = context;
        mOnClickListener = listener;
    }

    public interface OnListItemClickListener{
        void onClickItem(String path);
    }

    @Override
    public BBCContentAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int list_item_id = R.layout.content_list_item;

        View view = LayoutInflater.from(context).inflate(list_item_id, parent, false);
        return new BBCContentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BBCContentAdapterViewHolder holder, int position) {

        // Set cursor to position
        mCursor.moveToPosition(position);

        // Title set
        holder.mTitleTextView.setText(mCursor.getString(ContentListActivity.TITLE_INDEX));

        // Time set
        holder.mTimeTextView.setText(mCursor.getString(ContentListActivity.TIME_INDEX));

        // Description set
        holder.mDescriptionTextView.setText(mCursor.getString(ContentListActivity.DESCRIPTION_INDEX));

        // Use picasso to load image
        Picasso.with(mContext)
                .load(mCursor.getString(ContentListActivity.THUMBNAIL_INDEX))
                .resizeDimen(R.dimen.list_item_img_width, R.dimen.list_item_img_height)
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(holder.mThumbnailImageView);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder for BBC content adapter
     */
    public class BBCContentAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private ImageView mThumbnailImageView;
        private TextView mTitleTextView;
        private TextView mTimeTextView;
        private TextView mDescriptionTextView;

        public BBCContentAdapterViewHolder (View view) {
            super(view);
            mThumbnailImageView = (ImageView) view.findViewById(R.id.iv_thumbnail);
            mTitleTextView = (TextView) view.findViewById(R.id.tv_title);
            mTimeTextView = (TextView) view.findViewById(R.id.tv_time);
            mDescriptionTextView = (TextView) view.findViewById(R.id.tv_description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mCursor.moveToPosition(position);
            String path = mCursor.getString(ContentListActivity.TIMESTAMP_INDEX)
                    + "/"
                    + BBCContentContract.PATH_CATEGORY
                    + "/"
                    + mCursor.getString(ContentListActivity.CATEGORY_INDEX);
            mOnClickListener.onClickItem(path);
        }
    }
}
