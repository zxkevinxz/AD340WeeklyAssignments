package com.example.ad340weeklyassignments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad340weeklyassignments.databinding.ProfileFragmentBinding;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private final String TAG = ProfileFragment.class.getSimpleName();
    private ProfileFragmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle userInfo = getArguments();
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        TextView name = (TextView) view.findViewById(R.id.profileName);
        TextView age = (TextView) view.findViewById(R.id.age);
        TextView occupation = (TextView) view.findViewById(R.id.profileOccupation);
        TextView description = (TextView) view.findViewById(R.id.profileDescription);

        StringBuilder nameSB = new StringBuilder();
        nameSB.append(userInfo.getString(Constants.KEY_FIRSTNAME))
                .append(Constants.BLANK_SPACE)
                .append(userInfo.getString(Constants.KEY_LASTNAME));

        name.setText(nameSB.toString());
        age.setText(Integer.toString(userInfo.getInt(Constants.KEY_AGE)));
        occupation.setText(userInfo.getString(Constants.KEY_OCCUPATION));
        description.setText(userInfo.getString(Constants.KEY_DESCRIPTION));

        return view;
    }

}
