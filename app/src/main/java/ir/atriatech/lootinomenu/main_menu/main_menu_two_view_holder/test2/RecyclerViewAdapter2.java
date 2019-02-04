package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import ir.atriatech.lootinomenu.R;
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.FoodViewHolder;
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.SubMenuViewHolder;
import ir.atriatech.lootinomenu.model.Food;
import ir.atriatech.lootinomenu.model.SubMenu;

import static ir.atriatech.lootinomenu.ConstantsKt.TYPE_FOOD;
import static ir.atriatech.lootinomenu.ConstantsKt.TYPE_SUB_MENU;

public class RecyclerViewAdapter2 extends ListAdapter<Object, RecyclerView.ViewHolder> implements KmStickyListener {

    public RecyclerViewAdapter2() {
        super(ModelDiffUtilCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        if (viewType == TYPE_SUB_MENU) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_food_item, viewGroup, false);
            return new SubMenuViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_header_item, viewGroup, false);
            return new FoodViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == TYPE_SUB_MENU) {
            ((SubMenuViewHolder) viewHolder).bindHeaderUI((SubMenu) getItem(i));
        } else {
            ((FoodViewHolder) viewHolder).bindFoodUI((Food) getItem(i));
        }
    }




    @Override
    public int getItemViewType(int position) {
        if(getItem(position) instanceof SubMenu){
            return TYPE_SUB_MENU;

        }else {
            return TYPE_FOOD;
        }
    }

    @Override
    public Integer getHeaderPositionForItem(Integer itemPosition) {
        Integer headerPosition = 0;
        for (Integer i = itemPosition;i > 0 ;i--){
            if (isHeader(i)){
                headerPosition = i;
                return headerPosition;
            }
        }
        return headerPosition;
    }

    @Override
    public Integer getHeaderLayout(Integer headerPosition) {
        return R.layout.main_menu_header_item;
    }

    @Override
    public void bindHeaderData(View header, Integer headerPosition) {
        TextView tv = header.findViewById(R.id.txt_item_header_main_menu_recycler_view);
        tv.setText(((SubMenu)getItem(headerPosition)).getName());
    }

    @Override
    public Boolean isHeader(Integer itemPosition) {

        return (getItem(itemPosition)) instanceof SubMenu;
    }

    public static final DiffUtil.ItemCallback<Object> ModelDiffUtilCallback =
            new DiffUtil.ItemCallback<Object>() {
                @Override
                public boolean areItemsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    if (oldItem instanceof SubMenu && newItem instanceof SubMenu){
                        return true;

                    }else if(oldItem instanceof Food && newItem instanceof Food){
                        return true;
                    }else {
                        return false;
                    }
                }

                @Override
                public boolean areContentsTheSame(@NonNull Object oldItem, @NonNull Object newItem) {
                    if (oldItem instanceof SubMenu && newItem instanceof SubMenu && ((SubMenu) oldItem)
                            .getSubMenuId()==((SubMenu) newItem).getSubMenuId()){
                        return true;

                    }else if(oldItem instanceof Food && newItem instanceof Food&& ((Food) oldItem).getId()==((Food) newItem).getId()){
                        return true;
                    }else {
                        return false;
                    }
                }
            };
}
