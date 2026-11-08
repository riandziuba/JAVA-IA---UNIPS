# Testing guide - Javify Administrative

This guide works for both **Postman** and **Insomnia**. The files are in the same folder as this guide.

Available files:

- `javify-administrative.postman_collection.json` — collection with all requests.
- `javify-local.postman_environment.json` — Postman environment with the default variables.

## 1. Postman setup

### Import the collection

1. Open Postman.
2. Click **Import** > **File**.
3. Select `javify-administrative.postman_collection.json`.
4. The "Javify Administrative" collection will appear with the folders Plans, Addresses, Signatures, Credit Cards, Users and Phones.

### Import the environment

1. Click the environment dropdown (top right) and choose **Import**.
2. Select `javify-local.postman_environment.json`.
3. Activate the **Javify Local** environment.

### Test

The variables `{{baseUrl}}`, `{{planId}}`, `{{signatureId}}`, etc. will be resolved automatically.

---

## 2. Insomnia setup

### Import the collection

1. Open Insomnia.
2. In the desired workspace, click the gear icon or the **Import/Export** menu.
3. Choose **Import Data** > **From File**.
4. Select `javify-administrative.postman_collection.json`.
5. The folders (Plans, Addresses, Signatures, Credit Cards, Users, Phones) and the requests will appear in the workspace.

### Create the environment

1. In the bottom-left corner, click the environment dropdown (usually shown as "No Environment").
2. Choose **Manage Environments**.
3. Click **Add** and name it **Javify Local**.
4. Add the variables below (all as text/string):

```json
{
  "baseUrl": "http://localhost:8080",
  "planId": "1",
  "postalCode": "01001000",
  "signatureId": "1",
  "userId": "1",
  "phoneId": "1",
  "creditCardId": "1"
}
```

5. Save and select **Javify Local** in the environment dropdown.

---

## 3. Suggested testing order

1. **Plans** — Create plan
2. **Addresses** — Create address (you can use the example postal code `01001000`)
3. **Users > Create user** — Sends a complete `address` and `planId`; the address is saved if it does not exist and the signature is created automatically.
4. **Users > Create user with existing signature** — Sends a complete `address` and `signatureId` to link the user to an existing signature (useful for Family plan members).
5. **Phones** — Create phone for user
6. **Credit Cards** — Create credit card for signature

---

## 4. User payload examples

### Create user with a new address and a new signature

```json
{
  "name": "João Silva",
  "email": "joao.silva@example.com",
  "passwordHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
  "holder": true,
  "birthday": "1990-05-15",
  "address": {
    "postalCode": "01001000",
    "street": "Praça da Sé",
    "neighborhood": "Sé"
  },
  "planId": 1
}
```

### Create user linked to an existing signature

```json
{
  "name": "Maria Santos",
  "email": "maria.santos@example.com",
  "passwordHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
  "holder": false,
  "birthday": "1985-08-22",
  "address": {
    "postalCode": "01311000",
    "street": "Av. Paulista",
    "neighborhood": "Bela Vista"
  },
  "signatureId": 1
}
```

---

## 5. Important rules

- `address` is required when creating/updating a user.
- If the address postal code does not exist yet, it is saved automatically.
- If the postal code already exists, the user is linked to the existing address.
- `planId` takes priority: when provided, a new signature is created for the user.
- `signatureId` is used only when `planId` is not provided, to link an existing signature.
- The user response returns the complete address and signature (not just their ids).

---

## 6. Testing with curl

Each request in the collection already includes the equivalent `curl` command in its description. You can also use it directly in the terminal, replacing `{{baseUrl}}` with `http://localhost:8080` and the other variables with their actual values.

Example of user creation:

```bash
curl -X POST http://localhost:8080/api/users \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "João Silva",
    "email": "joao.silva@example.com",
    "passwordHash": "$2a$10$hashhashhashhashhashhashhashhashhashhashhashhashh",
    "holder": true,
    "birthday": "1990-05-15",
    "address": {
      "postalCode": "01001000",
      "street": "Praça da Sé",
      "neighborhood": "Sé"
    },
    "planId": 1
  }'
```
