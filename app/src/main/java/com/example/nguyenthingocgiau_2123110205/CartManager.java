package com.example.nguyenthingocgiau_2123110205;

import com.example.nguyenthingocgiau_2123110205.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    // Danh sách giỏ hàng dùng chung
    private static final List<CartItem> cartItems = new ArrayList<>();

    // Thêm sản phẩm vào giỏ
    public static void addToCart(CartItem item) {
        // Nếu sản phẩm đã có, tăng số lượng
        for (CartItem existingItem : cartItems) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    // Lấy danh sách sản phẩm trong giỏ
    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    // Xóa tất cả sản phẩm trong giỏ
    public static void clearCart() {
        cartItems.clear();
    }

    // Tính tổng tiền
    public static double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    // Xóa 1 món trong giỏ
    public static void removeItem(CartItem itemToRemove) {
        cartItems.remove(itemToRemove);
    }
}
