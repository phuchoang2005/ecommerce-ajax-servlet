## 1. Product Discovery (The Catalog)

This is where users browse and find what they want to buy.

- **Live Search & Filtering:**
  - **Ajax Search:** As the user types in a search bar, results update instantly without a page reload.
  - **Category Filter:** Clicking a category (from the `CATEGORIES` table) refreshes the product grid via Ajax.
- **Product Quick View:** Clicking an eye icon on a product card opens a Modal fetching details from the `PRODUCTS` table (name, unit, price, stock) using an asynchronous request.

## 2. Shopping Cart Management (The `CARTS` Logic)

This is the best place to practice **Ajax** because nobody likes a page refresh when adding an item.

- **"Add to Cart" Animation:** When clicking "Add," the system sends the `product_id` to a `CartServlet`. If the item exists in `CART_ITEMS`, increment `quantity`; otherwise, create a new entry.
- **Real-time Cart Sidebar:** A slide-out menu that calculates the subtotal dynamically.
- **Quantity Adjuster:** Users can click `+` or `-` in the cart. The `added_at` and `quantity` fields update in the background, and the total price updates instantly on the screen.

## 3. Checkout & Order Placement

Transitioning data from the "Temporary" (Cart) to the "Permanent" (Order).

- **One-Page Checkout:** \* Display the user's default info from the `PROFILES` table.
  - Allow them to edit the `address` or `phone` specifically for this order.
- **Order Confirmation:** Upon clicking "Place Order," the Servlet must:
  1.  Create a record in `ORDERS`.
  2.  Move all `CART_ITEMS` into `ORDER_DETAILS`.
  3.  Clear the `CART_ITEMS` for that user.
  4.  **Important:** Decrease the `stock` in the `PRODUCTS` table.

## 4. User Account & Personalization

Managing the `USERS` and `PROFILES` relationship.

- **Profile Management:** A tab where users can update their `full_name`, `email`, and `address`. Use Ajax to show a "Profile Updated Successfully" toast message.
- **Order Tracking:** A "My Orders" page fetching data from the `ORDERS` table.
  - Users can see the `status` (PENDING, SHIPPED, etc.).
  - **Cancel Order:** Allow users to change status to "CANCELLED" only if the current status is "PENDING".

---
