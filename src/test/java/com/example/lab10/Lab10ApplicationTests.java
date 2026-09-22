package com.example.lab10;

import com.example.lab10.model.Product;
import com.example.lab10.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

/**
 * Lab10ApplicationTests — ทดสอบ Reactive code
 *
 * ✅ test findById() ทำเสร็จแล้วเป็นตัวอย่าง
 * ❌ TODO: เพิ่ม test สำหรับ method ที่นักศึกษาทำเอง
 *
 * StepVerifier — วิธีทดสอบ Mono/Flux:
 *   StepVerifier.create(mono/flux)
 *     .expectNext(value)     ← คาดหวังค่าที่ได้
 *     .expectNextCount(n)    ← คาดหวังจำนวน element
 *     .verifyComplete()      ← ยืนยัน onComplete
 *     .verifyError()         ← ยืนยัน onError
 */
@SpringBootTest
class Lab10ApplicationTests {

    @Autowired
    private ProductRepository repository;

    // ══════════════════════════════════════════════════════
    // ✅ ตัวอย่าง test — ศึกษาแล้วเพิ่ม test เอง
    // ══════════════════════════════════════════════════════

    @Test
    void contextLoads() {
        // Spring Application Context โหลดสำเร็จ
    }

    @Test
    void testFindById_found() {
        // ✅ ตัวอย่าง: ทดสอบ findById ที่พบข้อมูล
        StepVerifier.create(repository.findById("1"))
                .expectNextMatches(p -> p.getName().contains("iPhone"))
                .verifyComplete();
    }

    @Test
    void testFindById_notFound() {
        // ✅ ตัวอย่าง: ทดสอบ findById ที่ไม่พบข้อมูล
        StepVerifier.create(repository.findById("999"))
                .verifyComplete(); // Mono.empty() → onComplete ทันที
    }

    // ══════════════════════════════════════════════════════
    // ✅ Unit Tests เพิ่มเติมสำหรับ Method ที่นักศึกษาพัฒนา
    // ══════════════════════════════════════════════════════

    @Test
    void testFindAll() {
        StepVerifier.create(repository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    void testSave() {
        Product newProduct = new Product("4", "iPad Pro M4", "Electronics", "Apple", 15, 39900.0, "MEMBER");
        StepVerifier.create(repository.save(newProduct))
                .expectNextMatches(p -> "4".equals(p.getId()) && "iPad Pro M4".equals(p.getName()))
                .verifyComplete();

        StepVerifier.create(repository.findById("4"))
                .expectNextMatches(p -> "iPad Pro M4".equals(p.getName()))
                .verifyComplete();
    }

    @Test
    void testFindByCategory() {
        StepVerifier.create(repository.findByCategory("Electronics"))
                .thenConsumeWhile(p -> "Electronics".equalsIgnoreCase(p.getCategory()))
                .verifyComplete();
    }

    @Test
    void testDeleteById() {
        StepVerifier.create(repository.deleteById("3"))
                .verifyComplete();

        StepVerifier.create(repository.findById("3"))
                .verifyComplete();
    }
}
