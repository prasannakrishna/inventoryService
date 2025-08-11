package com.bhagwat.scm.inventoryService.mapper;

import com.bhagwat.scm.inventoryService.dto.ProductDTO;
import com.bhagwat.scm.inventoryService.entity.PackConfiguration;
import com.bhagwat.scm.inventoryService.entity.Product;
import com.bhagwat.scm.inventoryService.entity.ProductDocument;
import com.bhagwat.scm.inventoryService.entity.SKU;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapper {

    // Map Request DTO -> Entity
    public static Product toEntity(ProductDTO request) {
        Product entity = new Product();
        return entity;
    }

    public static ProductDocument toDocument(Product product, SKU sku, PackConfiguration packConfiguration) {
        ProductDocument doc = new ProductDocument();

        // Map Product attributes
        doc.setProductId(product.getProductId());
        doc.setSellerId(product.getSellerId());
        doc.setProductName(product.getProductName());
        doc.setBrand(product.getBrand());
        doc.setDescription(product.getDescription());
        doc.setHashKeys(product.getHashKeys());
        doc.setSeasonal(product.isSeasonal());
        doc.setPrice(product.getPrice());
        doc.setMRP_Price(product.getMRP_Price());
        doc.set_in_stock(product.is_in_stock());
        doc.setShipping_tracking_level(product.getShipping_tracking_level());
        doc.setFrequency(product.getFrequency());
        doc.setStore_tracking_level(product.getStore_tracking_level());
        doc.setCaptureExpiryDuringCreateInventory(product.isCaptureExpiryDuringCreateInventory());

        // Denormalize SKU details
        if (sku != null) {
           /* doc.setSkuId(sku.getSkuId());
            doc.setSkuName(sku.getSkuName());
            doc.setSkuProductGroup(sku.getProductGroup());
            doc.setSkuLength(sku.getLength());
            doc.setSkuWidth(sku.getWidth());
            doc.setSkuHeight(sku.getHeight());
            doc.setSkuWeight(sku.getWeight());
            doc.setSkuIsHazardous(sku.getIsHazardous());
            doc.setSkuTrackingLevel(sku.getTrackingLevel());
            doc.setSkuIsSellerSKU(sku.getIsSellerSKU());
            if(sku.getSellerId() != null)
                doc.setSkuSellerPartyId(Long.valueOf(sku.getSellerId()));
            doc.setSkuUomWeight(sku.getUomWeight());
            doc.setSkuUomVolume(sku.getUomVolume());
            doc.setSkuUomLength(sku.getUomLength());
            doc.setSkuUomDimension(sku.getUomDimension());
            doc.setSkuState(sku.getSkuState());
            doc.setSkuPackingTrackingLevel(sku.getPacking_tracking_level());
            doc.setSkuShippingTrackingLevel(sku.getShipping_tracking_level());*/
        }

        // Embed PackConfiguration directly
        doc.setPackingConfiguration(packConfiguration);

        return doc;
    }

    /**
     * Maps a ProductDocument back to a Product entity.
     * WARNING: In a strict CQRS pattern, this mapping from a denormalized
     * query model back to a normalized entity is generally not performed,
     * as the ProductDocument is optimized for reads and may not contain
     * all the necessary normalized relationships (e.g., full SKU object,
     * Party object for seller).
     *
     * This method provides a basic conversion for direct Product attributes,
     * but will not fully reconstruct complex relationships.
     *
     * @param doc The ProductDocument from the query side.
     * @return A Product entity (potentially incomplete in terms of relationships).
     */
    public static Product toEntity(ProductDocument doc) {
        Product entity = new Product();

        // Map Product attributes
        entity.setProductId(doc.getProductId());
        entity.setSellerId(doc.getSellerId());
        entity.setProductName(doc.getProductName());
        entity.setBrand(doc.getBrand());
        entity.setDescription(doc.getDescription());
        entity.setHashKeys(doc.getHashKeys());
        entity.setSeasonal(doc.isSeasonal());
        entity.setPrice(doc.getPrice());
        entity.setMRP_Price(doc.getMRP_Price());
        entity.set_in_stock(doc.is_in_stock());
        entity.setShipping_tracking_level(doc.getShipping_tracking_level());
        entity.setFrequency(doc.getFrequency());
        entity.setStore_tracking_level(doc.getStore_tracking_level());
        entity.setCaptureExpiryDuringCreateInventory(doc.isCaptureExpiryDuringCreateInventory());

        // Note: Reconstructing the full SKU and PackConfiguration objects
        // with all their relationships from the denormalized document
        // is complex and usually not done this way in CQRS.
        // You would typically query the command database for full entity reconstruction if needed.
        // For simplicity, we are not setting the SKU or PackConfiguration entities here.
        // If you need to map SKU details, you would create a new SKU object and set its properties.
        // For example:
        /*
        SKU sku = new SKU();
        sku.setSkuId(doc.getSkuId());
        sku.setSkuName(doc.getSkuName());
        // ... set other SKU properties
        entity.setSku(sku);
        */

        return entity;
    }
}
