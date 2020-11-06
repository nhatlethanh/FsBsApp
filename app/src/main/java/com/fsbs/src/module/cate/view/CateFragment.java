package com.fsbs.src.module.cate.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import com.fsbs.R;
import com.fsbs.src.model.Cate;
import com.fsbs.src.module.cate.ICate;
import com.fsbs.src.module.cate.presenter.CateAdapter;
import com.fsbs.src.module.cate.presenter.PresenterCate;
import com.fsbs.src.utils.ItemOffsetDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CateFragment extends Fragment implements ICate.IViewCate,ICate.IOnClick {

    private PresenterCate presenterCate;
    private  RecyclerView recyclerCate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cate, container, false);
        presenterCate = new PresenterCate(this,null);
        presenterCate.getListCate();
        initView(view);
        return view;

    }

    private void initView(View view) {
        recyclerCate = view.findViewById(R.id.recyclerCate);

    }

    @Override
    public void onGetListSuccess(List<Cate> cateList) {
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin5);;

        CateAdapter cateAdapter = new CateAdapter(getActivity(),R.layout.custom_item_cate,cateList,this);
        recyclerCate.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL));
        recyclerCate.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        recyclerCate.setAdapter(cateAdapter);
        cateAdapter.notifyDataSetChanged();


    }

    @Override
    public void onGetListFailed(String msg) {
    }

    @Override
    public void onClickGetProductForCate(String cateId,String cateName) {
        Intent intent = new Intent(getActivity(),ProductForCateActivity.class);
        intent.putExtra("cateId" , cateId);
        intent.putExtra("cateName" , cateName);
        startActivity(intent);
    }
}
