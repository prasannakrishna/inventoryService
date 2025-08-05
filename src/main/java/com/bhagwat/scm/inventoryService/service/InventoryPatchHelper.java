package com.bhagwat.scm.inventoryService.service;

import com.bhagwat.scm.inventoryService.command.InventoryCommandDTO;
import com.bhagwat.scm.inventoryService.entity.Inventory;
import com.bhagwat.scm.inventoryService.mapper.InventoryMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.json.JsonMergePatch;

@Component
public class InventoryPatchHelper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public InventoryCommandDTO applyPatch(Inventory entity, JsonMergePatch patch) throws JsonProcessingException {
        InventoryCommandDTO dto = mapEntityToDto(entity);
        JsonNode node = objectMapper.valueToTree(dto);
        return null;
        //JsonNode patched = patch.apply(node);
        //return objectMapper.treeToValue(patched, InventoryCommandDTO.class);
    }

    private InventoryCommandDTO mapEntityToDto(Inventory entity) {
        // same mapping as InventoryMapper.toDto()
        return new InventoryMapper().toDto(entity);
    }
}
