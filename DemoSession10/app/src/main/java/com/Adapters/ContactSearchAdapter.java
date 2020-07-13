package com.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.demo.R;
import com.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactSearchAdapter extends ArrayAdapter<Contact> {
    private Context context;
    private int layout;
    private List<Contact> contacts;
    private List<Contact> filterContacts = new ArrayList<Contact>();
    public ContactSearchAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.contacts = objects;
    }

    @Override
    public int getCount() {
        return filterContacts.size();
    }

    @NonNull
    @Override
    public Filter getFilter(){
        return new ContactFilter(this,contacts);
    }

    @NonNull
    @Override
    public Contact getItem(int position){
        return filterContacts.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layout, null);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);
        ImageView imageViewPhoto = view.findViewById(R.id.imageViewPhoto);
        Contact contact = filterContacts.get(position);
        textViewName.setText(contact.getName());
        textViewPhone.setText(contact.getPhone());
        imageViewPhoto.setImageResource(contact.getPhoto());
        return view;
    }
    private class ContactFilter extends Filter
    {
        ContactSearchAdapter contactSearchAdapter;
        List<Contact> contacts;
        List<Contact> filterContacts;
        public ContactFilter(ContactSearchAdapter contactSearchAdapter, List<Contact> contacts){
            this.contactSearchAdapter = contactSearchAdapter;
            this.contacts = contacts;
            this.filterContacts = new ArrayList<Contact>();
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filterContacts.clear();
            FilterResults filterResults = new FilterResults();
            if(constraint == null || constraint.length()==0){
                filterContacts.addAll(contacts);
            }else{
                for (Contact contact: contacts) {
                    if(contact.getName().toLowerCase().contains((constraint.toString().toLowerCase()))){
                        filterContacts.add(contact);
                    }
                }
            }
            filterResults.values = filterContacts;
            filterResults.count = filterContacts.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            contactSearchAdapter.filterContacts.clear();
            contactSearchAdapter.filterContacts.addAll((List)results.values);
            contactSearchAdapter.notifyDataSetChanged();
        }
    }
}
