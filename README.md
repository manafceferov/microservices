🧩 Microservice E-Commerce System (Spring Boot)

Bu layihə, mikroservis arxitekturası üzərində qurulmuş e-ticarət tətbiqidir. Layihə bir-biri ilə əlaqəli müstəqil servislərdən ibarətdir və hər servis öz məsuliyyət sahəsini idarə edir.

🚀 Servisər
- **api-gateway** — Bütün sorğuları müvafiq servislərə yönləndirən mərkəzi giriş nöqtəsi (port: 9999)
- **user-service** — İstifadəçi qeydiyyatı, autentifikasiya və profil idarəsi (port: 1111)
- **product-service** — Məhsul kataloqu və idarəetməsi (port: 2222)
- **order-service** — Sifariş yaradılması və izlənməsi (port: 3333)
- **cart-service** — Səbət idarəetməsi və checkout prosesi (port: 5555)
- **admin-service** — Admin paneli — istifadəçilər, məhsullar və sifarişlər üzərində tam nəzarət (port: 4444)

🔐 Əsas Funksionallıqlar
İkili Rol Sistemi (RBAC): ROLE_USER və ROLE_ADMIN üçün fərqli icazələr (Spring Security & JWT).

İstifadəçi tərəfi: Qeydiyyat, giriş, profil idarəsi, məhsullara baxış, səbətə əlavə etmə, sifariş yaratma və sifariş tarixçəsi.

Admin tərəfi: Bütün istifadəçiləri, məhsulları və sifarişləri idarə etmə, sifariş statusunu dəyişdirmə, rol təyini.

Servislərarası Kommunikasiya: OpenFeign vasitəsilə servislərarası HTTP sorğular.

Database Versioning: Hər servisin verilənlər bazası sxeminin idarə olunması üçün Liquibase istifadə edilib.

Konteynerləşdirmə: Layihə Docker vasitəsilə asanlıqla işə salına bilər.

🛠 Texnologiyalar
- Framework: Spring Boot, Spring Cloud, Spring Security
- Dillər: Java və Kotlin (Entity və DTO təbəqələri üçün)
- Verilənlər Bazası: PostgreSQL
- Miqrasiya: Liquibase
- Servislərarası: OpenFeign
- Gateway: Spring Cloud Gateway MVC
- Authentication: JWT Token
- Mapper: MapStruct
- DevOps: Docker & Docker Compose
- Dokumentasiya: Swagger UI

🏗 Arxitektura
Bu layihədə hər servis müstəqil deploy edilə bilən strukturdadır. API Gateway bütün xarici sorğuları qarşılayır və müvafiq servisə yönləndirir. Servislərarası kommunikasiya OpenFeign vasitəsilə həyata keçirilir.
