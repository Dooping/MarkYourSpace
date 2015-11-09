package nf.co.markyourspace.markyourspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * Created by Guilherme on 09/11/2015.
 */
public class RoomEntryAdapter extends ArrayAdapter {
    public RoomEntryAdapter(Context context, String [] rooms) {
        super(context,R.layout.room_entry ,rooms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView;
        if (convertView == null)
            customView = LayoutInflater.from(getContext()).inflate(R.layout.room_entry, parent, false);
        else
            customView = convertView;
        String singleRoomItem = (String) getItem(position);
        TextView roomName = (TextView)customView.findViewById(R.id.roomProperties);
        roomName.setText(singleRoomItem);
        return customView;
    }
}
