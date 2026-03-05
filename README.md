# 🎓 LMS Toy Project

React + Vite + Spring Boot 기반으로  
학습관리시스템(LMS)의 핵심 기능을 구현하는 토이 프로젝트입니다.

실무에서 경험했던 JSP 기반 LMS 구조를 바탕으로  
프론트엔드와 백엔드를 분리한 **REST API 기반 웹 아키텍처로 재구성**하며  
수강 흐름과 출석 판정 로직을 중심으로 기능을 구현하고 있습니다.

⚙️ **Work in Progress**  
이 프로젝트는 LMS 핵심 기능을 단계적으로 구현하며 지속적으로 업데이트되고 있습니다.

---

# 🎯 Project Goals

- 기존 LMS 핵심 기능을 **React 기반 구조로 재구현**
- **REST API 기반 프론트엔드 / 백엔드 분리 구조 설계**
- **수강 기록 관리 및 출석 판정 로직 구현**
- 실무 경험을 기반으로 **LMS 서비스 흐름 재구성**

---

# 🛠 Tech Stack

## Backend
- Spring Boot
- Spring Security
- Java
- JWT
- MyBatis

## Frontend
- React
- Vite
- Emotion

## Database
- MySQL

---

# 📌 주요 기능 (Planned)

- JWT 기반 회원 인증 (로그인)
- 강의 목록 조회
- 강의 수강 (영상 재생)
- 수강 기록 관리
- 출석 판정 로직
- 강의 학습 이력 조회

---

# 🚀 Development Progress

- 프로젝트 초기 세팅
- React + Vite 환경 구성
- Spring Boot API 서버 구성
- 인증 및 수강 기능 구현 예정

---

# 🛠 협업 규칙 (Convention)

## 📝 Commit Message 규칙

### Format

```
Type: 변경 내용 요약 (#이슈번호)
```

### Example

```
Feat: 로그인 API 구현 (#12)
Fix: 출석 판정 로직 버그 수정 (#21)
Docs: README 문서 수정
```

### Types

- **Feat**: 새로운 기능 추가
- **Fix**: 버그 수정
- **Docs**: 문서 수정
- **Style**: 코드 포맷팅 (기능 변경 없음)
- **Refactor**: 코드 리팩토링
- **Chore**: 빌드 설정 및 기타 작업

---

## 🌿 Branch 전략

- **main**: 배포 브랜치
- **develop**: 개발 통합 브랜치
- **feature/기능설명-이슈번호**: 기능 개발 브랜치

### Branch Example

```
feature/login-12
feature/attendance-logic-21
```

---

## 🚀 Workflow

1. Issue 생성  
2. `feature/기능설명-이슈번호` 브랜치 생성  
3. 기능 개발 및 Commit  
4. `develop` 브랜치로 Pull Request 생성  
5. 코드 리뷰 후 Merge