package com.thvkonst.mycartlist;

public interface ItemsAdapterListener {
     void OnItemClick(Record record, int position);
     void OnItemLongClick(Record record, int position);
}
