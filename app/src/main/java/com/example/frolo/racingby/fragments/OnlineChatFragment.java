package com.example.frolo.racingby.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frolo.racingby.Message;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.example.frolo.racingby.R;

public class OnlineChatFragment extends Fragment {

    private FirebaseListAdapter<Message> adapter;
    RelativeLayout OnlineChatFragment;
    Button button, buttonLeave;
    EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_online_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Online-чат");
        OnlineChatFragment = this.getActivity().findViewById(R.id.online_chat_item);
        button = this.getActivity().findViewById(R.id.button2);
        buttonLeave = this.getActivity().findViewById(R.id.LeaveChat);
        input = view.findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().push()
                        .setValue(new Message(input.getText().toString(),
                                FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                input.setText("");
                return;
            }
        });

        final ListView listMessages = this.getActivity().findViewById(R.id.listView);
        adapter = new FirebaseListAdapter<Message>(getActivity(), Message.class, R.layout.chat_item,
                FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {

                TextView textMessage, autor, timeMessage;
                textMessage = v.findViewById(R.id.tvMessage);
                autor = v.findViewById(R.id.tvUser);
                timeMessage = v.findViewById(R.id.tvTime);

                textMessage.setText(model.getTextMessage());
                autor.setText(model.getAutor());
                timeMessage.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTimeMessage()));
                listMessages.setSelection(listMessages.getAdapter().getCount()-1);
            }
        };
        listMessages.setAdapter(adapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
