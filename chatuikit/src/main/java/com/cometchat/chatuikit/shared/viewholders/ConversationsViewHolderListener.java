package com.cometchat.chatuikit.shared.viewholders;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.chat.models.Conversation;
import com.cometchat.chatuikit.databinding.CometchatConversationsListItemsBinding;
import com.cometchat.chatuikit.shared.interfaces.ViewHolderCallBack;

import java.util.List;

public abstract class ConversationsViewHolderListener implements ViewHolderCallBack {
    private static final String TAG = ConversationsViewHolderListener.class.getSimpleName();

    public abstract View createView(Context context, CometchatConversationsListItemsBinding listItem);

    public abstract void bindView(Context context, View createdView, Conversation conversation, RecyclerView.ViewHolder holder, List<Conversation> conversationList, int position);
}
