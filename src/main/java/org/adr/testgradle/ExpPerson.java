package org.adr.testgradle;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exp_person", schema = "exp")
@SequenceGenerator(name = "exp_person_sq", sequenceName = "exp_person_sq", allocationSize = 1)
public class ExpPerson {
    @Id
    @Column(name = "per_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exp_person_sq")
    private Long perId;

    @Column(name = "per_uuid", nullable = false, unique = true)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID perUUID;

    @Column(name = "per_mail", nullable = false, length = 100, unique = true)
    private String perMail;

    @Column(name = "per_name", nullable = false, length = 60)
    private String perName;

    @Column(name = "per_lastname",nullable = false, length = 60)
    private String perLastname;

    @Column(name = "per_password",nullable = false, length = 255)
    private String perPassword;

    @Column(name = "per_avatar")
    private byte[] perAvatar;

    @Column(name = "per_last_access", columnDefinition = "timestamp")
    private LocalDateTime lastAccess;

    @Column(name = "created_at")
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDeleted;

    public ExpPerson() {}

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public UUID getPerUUID() {
        return perUUID;
    }

    public void setPerUUID(UUID perUUID) {
        this.perUUID = perUUID;
    }

    public String getPerMail() {
        return perMail;
    }

    public void setPerMail(String perMail) {
        this.perMail = perMail;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerLastname() {
        return perLastname;
    }

    public void setPerLastname(String perLastname) {
        this.perLastname = perLastname;
    }

    public String getPerPassword() {
        return perPassword;
    }

    public void setPerPassword(String perPassword) {
        this.perPassword = perPassword;
    }

    public byte[] getPerAvatar() {
        return perAvatar;
    }

    public void setPerAvatar(byte[] perAvatar) {
        this.perAvatar = perAvatar;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
