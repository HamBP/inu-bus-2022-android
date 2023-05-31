# 인입런

인천대입구역 → 학교 : 1.5km

오전 수업 시작 20분 전 버스 앞에 줄 서 있는 수백명… 지각하지 않기 위해 인천대입구역에서 달리는 것을 우리는 “인입런”이라고 불러요.<br>
학교로 향하는 버스는 인천대입구역 2번 출구에만 있는 것이 아니에요. 이 앱은 당신의 등하교를 도와줄 “인입런”이에요.

<div style="display:flex; justify-content:space-around;">
  <img style="width: 22%;" src="https://github.com/HamBP/inu-bus-2022-android/assets/35232655/84f13fb0-b778-4a9b-ac3b-f0f0a2544745"/>
  <img style="width: 22%;" src="https://github.com/HamBP/inu-bus-2022-android/assets/35232655/47bf3073-c389-4280-9345-f0b753a30c21"/>
  <img style="width: 22%;" src="https://github.com/HamBP/inu-bus-2022-android/assets/35232655/376b265a-fbf3-4be9-b8c3-33813bc10d70"/>
  <img style="width: 22%;" src="https://github.com/HamBP/inu-bus-2022-android/assets/35232655/e5a0777a-9c08-425d-ac60-455a046e26bb"/>
</div>

### ❓ 어떻게 사용하나요?

- 버스의 예상 도착시간과 정차하는 정류장 정보를 알려줘요.
- 탑승 위치를 확인할 수 있어요.
- 당겨서 새로고침할 수 있어요.

### 💡 리뉴얼버전에서 추가되었어요!

- 하교 버스 정보가 추가되었어요!
- 이제 버스의 현재 위치도 알려줘요!
- 도착 지점을 필터링할 수 있어요!

### 😢 아직 완벽하지 않아요

- 공과대학에서는 종점이기 때문에 운행중인 하교 버스 정보 조회가 어려워요.
- 공공 데이터 서버의 트래픽 문제로 간혹 데이터를 느리게 불러올 수 있어요.
- 버스 노선 변경은 즉시 대응하기 어렵지만 곧 업데이트 될 거예요.

## 개발

- DIP를 적용해 의존 관계를 단방향으로 설정했어요.
- 코드를 개선하고 있어요. 특히 Bus 데이터를 다루는 유틸을 위주로 리팩토링하고 있어요.
