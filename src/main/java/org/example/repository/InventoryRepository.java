package org.example.repository;

import org.example.model.Inventory;

public class InventoryRepository extends BaseRepository<Inventory,Integer> {

    public InventoryRepository() {
        super(Inventory.class);
    }
}
