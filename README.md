# Android Template

## 화면

![frame](https://user-images.githubusercontent.com/35232655/146656387-e59ca9dc-6463-4308-a99b-0475d613571f.jpg)

## 목적

- 10페이지 이내 간단한 앱 개발

## 의존성 - 라이브러리

- Navigation - Jetpack
- Koin - DI
- MVVM - AAC
- Retrofit2 - 통신

## 패키지 구조

- feature (UI)
    - main
        - MainActivity (NavHost 를 위한 컨테이너)
        - MainViewModel (전역 상태 관리)
    - home
        - HomeFragment
        - HomeViewModel
    - memo
        - MemoFragment
        - MemoViewModel
    
    ...
    
- global
    - base (BaseActivity, BaseFragment)
    - constant (상수)
    - di (의존관계 주입 koin)
    - usecase (기능)
    - util (개발자 정의 라이브러리)
    - configs (필요에 따라 .gitignore 로 추가해 주세요.)
    - store (전역변수로 사용 중이나 수정 고려 중)
- data
    - model (Entity)
    - repository (로컬 혹은 서버 DB 에 접근)

## 패키지별 의존 관계

- feature - 각각의 화면 단위 (global 의존 O, data\model 의존 O, data\repository 의존 X)
- global - feature 에서 사용할 기능등 정의 (feature 의존 X, data 의존 O)
- data - DB 접근 로직 및 Entity 정의 (다른 패키지에 의존 X)

## 예제

### 기능

- 사용자가 저장한 마지막 메모를 가져온다.
    - 화면1 : 로컬에서 가져오기, 서버에서 가져오기
    - 화면2 : 이전 데이터 출력 및 새로운 데이터 저장 가능
