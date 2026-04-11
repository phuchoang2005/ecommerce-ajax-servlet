## 1. Inventory & Product Management

This is the core of your e-commerce engine.

- **Dynamic Product Dashboard:** Use Ajax to fetch real-time stats: total products, out-of-stock items (stock < 5), and category distribution.
- **Product CRUD with File Upload:** \* Implement product creation using `Part` interface in Servlet 3.0 for image uploads.
  - **Ajax Inline Editing:** Allow the Admin to update `price` or `stock` directly within the table cells without a page reload.
- **Category Organizer:** Manage the `CATEGORIES` table to ensure products are correctly mapped for the customer-facing side.

## 2. Order Fulfillment System

Where the business logic happens.

- **Order Status Pipeline:** A dashboard that filters orders by `status` (PENDING, PAID, SHIPPED, etc.) using Ajax.
- **Transaction Processing:** When an Admin marks an order as "COMPLETED":
  - Trigger a backend logic to verify stock.
  - Update the `ORDERS` status.
  - Return a success/fail JSON response to update the UI instantly.
- **Detailed Order Inspection (Modals):** Clicking an Order ID opens a modal that joins `ORDER_DETAILS`, `PRODUCTS`, and `PROFILES` to show exactly what was bought and where to ship it.

## 3. User & CRM (Customer Relationship Management)

Managing the people behind the purchases.

- **Customer Profiles:** A unified view joining `USERS` and `PROFILES` to display contact details, addresses, and account age.
- **Purchase History & LTV:** Calculate the **Lifetime Value (LTV)** of a user by summing their `total_amount` from the `ORDERS` table.
- **Access Control:** A toggle interface to switch user roles between `ADMIN` and `USER`.

## 4. Analytics & Insights (Advanced)

Perfect for practicing complex SQL queries (JOINS, GROUP BY, AGGREGATIONS).

- **Revenue Analytics:** Use **Chart.js** on the frontend and a Servlet on the backend to provide data for a "Revenue over the last 30 days" line chart.
- **Top Selling Products:** Query the `ORDER_DETAILS` table to find the top 5 products by quantity sold.
- **Abandoned Cart Tracking:** Analyze `CART_ITEMS` that haven't been converted into `ORDERS` to identify which products are being "ignored" at checkout.

---

### Recommended Technical Workflow

To get the most out of your "Back to Basics" approach, I suggest the following stack:

| Layer           | Technology                                   | Purpose                                                                    |
| :-------------- | :------------------------------------------- | :------------------------------------------------------------------------- |
| **Frontend**    | HTML5, Bootstrap, **Vanilla JS (Fetch API)** | Handle UI and asynchronous requests to the server.                         |
| **Controller**  | **Java Servlets**                            | Handle Routing, Session Management, and JSON parsing (using Gson/Jackson). |
| **Data Access** | **JDBC (DAO Pattern)**                       | Writing raw SQL to interact with your schema.                              |
| **Data Format** | **JSON**                                     | The bridge between Java objects and JavaScript.                            |

**Example Ajax Workflow for your Project:**

1.  **Action:** Admin clicks "Delete Product".
2.  **Request:** JavaScript `fetch()` sends a `DELETE` request with the `product_id`.
3.  **Processing:** Your `ProductServlet` receives the ID, calls `ProductDAO.delete()`, and checks for foreign key constraints (e.g., if the product is in a cart).
4.  **Response:** Servlet sends back `{"success": true}`.
5.  **Update:** JavaScript removes the table row dynamically with a smooth fade-out effect.
