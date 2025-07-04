# 📄 Local HTTPS Proxy with NGINX for Google OAuth (Windows)

---

## 🔒 Local HTTPS with NGINX Reverse Proxy

This project uses **Google OAuth 2.0 (OIDC)** for authentication. To test OAuth **locally with HTTPS**, we use **NGINX** as a reverse proxy with a self-signed certificate.

---

## ✅ Why?

Google OAuth **requires** a **public HTTPS redirect URI**. When running locally, you can simulate this with:

- A custom domain (`quickhelp-hub.com`)
- A local NGINX reverse proxy
- A self-signed SSL certificate

---

## 🗂️ Local Setup Steps

### 1️⃣ Install NGINX for Windows

- Download the latest **NGINX for Windows**: [nginx.org/en/download.html](https://nginx.org/en/download.html)
- Unzip to `C:\nginx`

### 2️⃣ Install OpenSSL for Windows

To generate a self-signed cert:

- Download OpenSSL for Windows: [slproweb.com/products/Win32OpenSSL.html](https://slproweb.com/products/Win32OpenSSL.html)
- Install it.
- Add the OpenSSL `bin` folder to your `PATH`.

### 3️⃣ Generate a Self-Signed SSL Certificate

Open **Command Prompt**, navigate to your desired certs folder:

```bash
cd C:\nginx\certs

openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout quickhelp-hub.key -out quickhelp-hub.crt
```

👉 **Important:** When prompted for **Common Name**, enter:

```
quickhelp-hub.com
```

### 4️⃣ Map `quickhelp-hub.com` to Localhost

Edit your `hosts` file:

```
C:\Windows\System32\drivers\etc\hosts
```

Add:

```
127.0.0.1 quickhelp-hub.com
```

**Tip:** You must edit this file **as Administrator**.

### 5️⃣ Update `nginx.conf`

Example `nginx.conf`:

```nginx
worker_processes 1;

events {
    worker_connections 1024;
}

http {
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;
    keepalive_timeout  65;

    server {
        listen       443 ssl;
        server_name  quickhelp-hub.com;

        ssl_certificate      C:/nginx/certs/quickhelp-hub.crt;
        ssl_certificate_key  C:/nginx/certs/quickhelp-hub.key;

        location / {
            proxy_pass http://127.0.0.1:19097;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }

    server {
        listen 80;
        server_name quickhelp-hub.com;

        return 301 https://$host$request_uri;
    }
}
```

✔️ Place `quickhelp-hub.crt` and `quickhelp-hub.key` in the correct path.

### 6️⃣ Start Your Spring Boot App

Run your Spring Boot application on:

```
http://localhost:19097
```

### 7️⃣ Start NGINX

From `C:\nginx`:

```bash
nginx -t   # test config
start nginx
```

Visit:

```
https://quickhelp-hub.com
```

### 8️⃣ Google OAuth Setup

In the Google Cloud Console:

- **Authorized redirect URI:**
  ```
  https://quickhelp-hub.com/login/oauth2/code/google
  ```

In `application.yml` or `application.properties`:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            redirect-uri: "{baseUrl}/login/oauth2/code/google"
```

### ⚠️ Trust Your Self-Signed Certificate

Browsers will show a warning for self-signed certs. To remove it:

1. Open **mmc.exe → Certificates snap-in → Local Computer**
2. Import `quickhelp-hub.crt` into **Trusted Root Certification Authorities**.

### ✅ Done!

You can now develop and test Google OAuth 2.0 locally over HTTPS.

To stop NGINX:

```bash
nginx -s stop
```

To reload config changes:

```bash
nginx -s reload
```

---

## 🟢 Ready for production?

For production, use a real domain and a valid certificate (e.g. **Let’s Encrypt**).

