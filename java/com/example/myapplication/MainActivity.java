package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.view.View;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the RecyclerView in the layout
        recyclerView = findViewById(R.id.recyclerView);

        // Create layout manager and set it to RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize itemList
        itemList = new ArrayList<>();
        itemList.add("Item 1");
        itemList.add("Item 2");
        itemList.add("Item 3");
        itemList.add("Item 4");
        itemList.add("Item 5");
        itemList.add("Item 6");
        itemList.add("Item 7");
        itemList.add("Item 8");
        itemList.add("Item 9");

        // Create adapter and set it to RecyclerView
        adapter = new ItemAdapter(this, itemList, this);
        recyclerView.setAdapter(adapter);
        
        checkEmptyState();
    }

    private void filterList(String query) {
        List<String> filteredList = new ArrayList<>();
        for (String item : itemList) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click here
        Toast.makeText(this, "Item clicked at position: " + position, Toast.LENGTH_SHORT).show();
    }

    // Method to add a new item to the list and notify adapter
    private void addItemToList(String newItem) {
        itemList.add(newItem);
        adapter.notifyItemInserted(itemList.size() - 1);
    }

    // Method to remove an item from the list and notify adapter
    private void removeItemFromList(int position) {
        itemList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    // Method to update an item in the list and notify adapter
    private void updateItemInList(int position, String updatedItem) {
        itemList.set(position, updatedItem);
        adapter.notifyItemChanged(position);
    }

    // Method to add an item with animation
    private void addItemWithAnimation(String newItem) {
        itemList.add(newItem);
        adapter.notifyItemInserted(itemList.size() - 1);
        recyclerView.smoothScrollToPosition(itemList.size() - 1);
    }
    private void checkEmptyState() {
        if (itemList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


}
