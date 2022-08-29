package com.example.mycalendar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Fragment1 extends Fragment {
    //변수 선언
    String fileName;
    Button btn_write;
    EditText editDiary;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_1, container, false);
        //
        DatePicker dp = view.findViewById(R.id.datePicker);
        editDiary = view.findViewById(R.id.editDiary);
        btn_write = view.findViewById(R.id.btn_write);

        int Year = dp.getYear();
        int Month = dp.getMonth();
        int Day = dp.getDayOfMonth();

        Log.d("DATE", Year + Month + Day + "");

        fileName = Year + "_" + (Month + 1) + "_" + Day + ".txt";
        dp.init(Year, Month, Day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("CLICK:", year + "_" + (monthOfYear + 1) + "_" + dayOfMonth);
                fileName = year + "_" + (monthOfYear + 1) + "+" + dayOfMonth + ".txt";

                String str = readDiary(fileName);
                editDiary.setText(str); //화면에 뿌려준다.

            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fileName에 해당하는 파일을 쓰기모드로 editDiary에 입력된 텍스트를 읽어서 쓴다.
                try {
                    FileOutputStream outfs = getContext().openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str = editDiary.getText().toString();
                    outfs.write(str.getBytes());
                    outfs.close();
                    Toast.makeText(getContext(), fileName + "파일을 저장했습니다.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return view;
    }

    //선택한 날짜에 해당하는 읽기 파일의 이름을 넘겨 받아서
    //그 파일이 있으면 보여주고 없으면 보여주지 않음
    private String readDiary(String fileName) {
        String diaryStr = null;
        //파일 처리
        try {
            FileInputStream infS = getContext().openFileInput(fileName); //열고
            byte[] txt = new byte[500];
            infS.read(txt); //읽고
            infS.close(); //닫고

            diaryStr =  new String(txt);
        } catch (IOException e) {
            editDiary.setText("일기 없음");
        }

        return diaryStr;
    }

//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
}