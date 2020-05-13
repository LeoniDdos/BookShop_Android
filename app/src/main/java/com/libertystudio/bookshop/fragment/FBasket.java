package com.libertystudio.bookshop.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.libertystudio.bookshop.MainActivity;
import com.libertystudio.bookshop.R;
import com.libertystudio.bookshop.data.BookAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FBasket.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FBasket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FBasket extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private MainActivity mainActivity;
    private ListView lvBasketBooks;
    private TextView tvSum;
    private Button btnBuy;

    public FBasket() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FBasket.
     */
    // TODO: Rename and change types and number of parameters
    public static FBasket newInstance(String param1, String param2) {
        FBasket fragment = new FBasket();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        initElements(view);

        return view;
    }

    private void initElements(View view) {
        mainActivity = (MainActivity) getActivity();

        lvBasketBooks = view.findViewById(R.id.lvBasketBooks);
        lvBasketBooks.setAdapter(new BookAdapter(mainActivity, mainActivity.getListBasketBooks()));

        tvSum = view.findViewById(R.id.tvSum);
        tvSum.setText(String.valueOf(mainActivity.getBasketSum()) + " руб.");

        btnBuy = view.findViewById(R.id.buttonBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mainActivity, "Книги успешно куплены", Toast.LENGTH_SHORT).show();
//                mainActivity.getListBasketBooks().clear();
//                lvBasketBooks.setAdapter(new BookAdapter(mainActivity, mainActivity.getListBasketBooks()));
//                tvSum.setText(String.valueOf(mainActivity.getBasketSum()) + " руб.");

                if (mainActivity.getListBasketBooks().size() > 0) {
                    Fragment fragmentPurchase = new FPurchase();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragmentPurchase).commit();
                    mainActivity.setTitle("Успешная покупка");
                }
                else {
                    Toast.makeText(mainActivity, "В корзине отсутствуют книги", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}