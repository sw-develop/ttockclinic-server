## 똑똑진료소 서버

### 프로젝트 버전 및 구성

Gradle

SpringBoot 2.5.3

Java 11 

---

### Local DB 연결을 위한 설정 (추후 AWS RDS로 변경 예정)

- Local DB 생성

```SQL
~% mysql -u root -p //mysql 접속

mysql> create database ttockclinic_db;
mysql> create user 'admin'@'%' identified by 'admin';
mysql> grant all on ttockclinic_db.* to 'admin'@'%';
```

터미널에서 mysql 접속 후 springboot와 연결할 데이터베이스와 해당 데이터베이스 사용자 생성 및 권한 부여 수행

---

### 코드 추가 위치

```text
src/main/java/com/ewhaenonymous/ttockclinic 
```

-> com.ewhaenonymous.ttockclinic 패키지 내에 코드 생성하면 됨! 

-> API 1차 설계 완료

