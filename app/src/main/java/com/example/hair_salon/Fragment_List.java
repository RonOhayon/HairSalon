package com.example.hair_salon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment_List extends Fragment {
    protected View view;
    private ListView Fl_LV_list;
    private ArrayList<DaySchedule> dSchedule;
    DayScheAdapter aryAdapt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        initList();


        return view;
    }

    public void initList() {
        if (dSchedule == null) {
            dSchedule = new ArrayList<DaySchedule>();
            dSchedule.add(0, new DaySchedule("Open", "10:00", "10:30", true));
            dSchedule.add(1, new DaySchedule("Open", "10:30", "11:00", true));
            dSchedule.add(2, new DaySchedule("Open", "11:00", "11:30", true));
            dSchedule.add(3, new DaySchedule("Open", "11:30", "12:00", true));
            dSchedule.add(4, new DaySchedule("Open", "12:00", "12:30", true));
            dSchedule.add(5, new DaySchedule("Open", "12:30", "13:00", true));
            dSchedule.add(6, new DaySchedule("Open", "13:00", "13:30", true));
            dSchedule.add(7, new DaySchedule("Open", "13:30", "14:00", true));
            dSchedule.add(8, new DaySchedule("Open", "14:00", "14:30", true));
            dSchedule.add(9, new DaySchedule("Open", "14:30", "14:00", true));
            dSchedule.add(10, new DaySchedule("open", "15:00", "15:30", true));
            dSchedule.add(11, new DaySchedule("Open", "15:00", "16:00", true));
            dSchedule.add(12, new DaySchedule("Open", "16:00", "16:30", true));
            dSchedule.add(13, new DaySchedule("Open", "16:30", "17:00", true));
            dSchedule.add(14, new DaySchedule("Open", "17:00", "17:30", true));
        }
        Fl_LV_list = view.findViewById(R.id.Fl_LV_list);
        aryAdapt = new DayScheAdapter(getActivity(),R.layout.list_schedule,dSchedule);
        Fl_LV_list.setAdapter(aryAdapt);
    }

}





