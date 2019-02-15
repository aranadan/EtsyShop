package com.fox.andrey.etsyshop.interfaces;

import com.fox.andrey.etsyshop.ActiveResult;

public interface CallIntent {
    void onSavedItemClick(ActiveResult item, String urlPhoto);
}
