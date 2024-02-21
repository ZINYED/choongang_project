![pms_main](https://github.com/ZINYED/choongang_project/assets/118190422/d4064752-2527-45da-b86d-5bc57402bcf8)

# 📖 프로젝트 소개 및 개요
![pms](https://github.com/ZINYED/choongang_project/assets/118190422/7bc6faa1-b409-4940-a66a-9418ec5c941d)
교육 기관의 학생층을 타겟으로 한 프로젝트 관리 시스템입니다.
프로젝트의 핵심 원리 및 개념을 쉽고 명확하게 이해할 수 있고, 교육 과정 중 진행하는 프로젝트에 적용해 업무 분할 및 일정 조율 등을 보다 효율적으로 할 수 있는 등의 경험을 쌓을 수 있습니다.

&nbsp;
# 💻 시스템 프로세스
![pms_process](https://github.com/ZINYED/choongang_project/assets/118190422/bacf0807-7d0a-4813-a126-c8ed426b051b)

&nbsp;
# 🕛 프로젝트 기간
2023/10/25 ~ 2023/11/30

&nbsp;
# 🛠 개발환경 및 사용 기술
![pms_use](https://github.com/ZINYED/choongang_project/assets/118190422/01265bc0-6d23-4e16-a219-72b7ea4b2f35)

&nbsp;
# 🙌🏻 프로젝트 팀원 및 역할
|이름|역할|
|---|---|
|👑 팀장 황인정|프로젝트 생성, 프로젝트 관리, 관리자 페이지, 통합 검색|
|😀 부팀장 이진희|프로젝트용 캘린더, 프로젝트 게시판 - 회의록, 알림|
|😀 팀원 강준우|채팅, 관리자 페이지|
|😀 팀원 문경훈|회원 관리 및 마이 페이지|
|😀 팀원 이광현|프로젝트 상세 작업 문서, 휴지통, 팀원별 진척률|
|😀 팀원 조미혜|프로젝트 게시판, 완료 프로젝트 목록|
|😀 팀원 차예지|전체 게시판, To-Do List|

&nbsp;
# 📍 ERD
![erd](https://github.com/ZINYED/choongang_project/assets/118190422/c90a9b77-c40f-48f5-b8e0-961095f3b2cb)

&nbsp;
# ✨ 나의 구현 기능

## 🗓 프로젝트 캘린더
![calendar_process](https://github.com/ZINYED/choongang_project/assets/118190422/0384390a-b566-43a9-ba9f-21e3e4b595b4)
- FullCalendar 라이브러리를 이용해 기본적인 캘린더 화면이 출력되도록 했습니다.
- 접속한 사용자가 참여중인 프로젝트의 전체 기간과 및 회의 일정을 확인할 수 있습니다.

&nbsp;
### 🗓 프로젝트 캘린더 기능
![calendar_detail](https://github.com/ZINYED/choongang_project/assets/118190422/3c0f25d9-13b0-423d-ade1-04acdd6b3f94)
- 세션에 저장된 Userinfo의 project_id로 해당 프로젝트의 전체 기간과 회의 일정을 가져와 각각 다른 색상으로 이벤트 추가하여 표시했습니다.
- 파란색으로 표시된 프로젝트 기간 클릭 시 해당 프로젝트의 프로젝트 Home으로 이동합니다.

&nbsp;
## 💬 프로젝트 게시판 - 회의록
![meeting_process](https://github.com/ZINYED/choongang_project/assets/118190422/c2637939-e628-4af4-8c53-01e433c40f6e)
- 회의 일정을 등록, 수정, 삭제할 수 있습니다.
- 회의록 등록 시 회의 일정, 회의록을 선택하여 등록할 수 있도록 했으며, 해당 상태에 따라 meeting_status 값이 다르게 입력되어 각각 다른 색상으로 구분됩니다.
- 프로젝트의 전체 회의 일정 중 본인이 참석자로 지정된 회의만 확인할 수 있습니다.

&nbsp;
### 💬 회의록 등록 / 수정 / 삭제
![meeting_detail](https://github.com/ZINYED/choongang_project/assets/118190422/b8770704-db1c-4942-8655-87e92bbe0bca)

&nbsp;
### 💬 회의록 등록 / 수정 / 삭제 프로세스
![meeting_process2](https://github.com/ZINYED/choongang_project/assets/118190422/3330f5aa-ccc7-4125-8d28-c7396d628764)

MEETING, MEETING_MEMBER 두 Table 모두 **project_id**와 **meeting_id**를 **PK**로 사용합니다.

MEETING Table에 Insert 시, project_id의 경우 세션에 저장된 유저 정보에서 값을 가져오고,
**meeting_id**는 MEETING Table에서 **MAX+1 값**을 생성합니다.

이후 MEETING_MEMBER Table에 Insert 시, 이전에 생성한 **meeting_id**의 **MAX 값**을 가져옵니다.
MEETING_MEMBER Table은 **project_id, meeting_id, meetuser_id 3개의 PK**를 사용하여
참석자의 user_id를 meetuser_id로 저장해 참석자 수 만큼 **다중 Row Insert** 처리합니다.

&nbsp;
### 💬 회의록 목록
![meeting_list](https://github.com/ZINYED/choongang_project/assets/118190422/cf93b850-c0b1-4205-b2ba-04562fdad8ad)

회의록 버튼 클릭 시 AJAX를 사용해 캘린더 오른쪽으로 회의록 리스트가 표출되도록 했습니다.

회의록 리스트에서 하나의 회의록을 클릭하면 클릭하면 보라색 이벤트를 클릭했을 때와 동일하게 회의록 상세 페이지로 이동합니다.

&nbsp;
## 📌 알림
![alarm_process](https://github.com/ZINYED/choongang_project/assets/118190422/addd4d98-bf6e-4f37-a8d4-0e07638aa0dd)

- 학생, 팀장, 관리자 권한에 따라 다른 실시간 알림이 표시됩니다.
- WebSocket과 Stomp를 이용하여 기능을 구현했으며, 5초마다 WebSocket이 재연결되어 재접속하거나 새로고침을 하지 않아도 새로운 알림이 수신됩니다.

&nbsp;
### 📌 WebSocket / Stomp 프로세스
![alarm_websocket](https://github.com/ZINYED/choongang_project/assets/118190422/3482ea05-d8aa-480a-a086-01e5368fe72b)

**5초 마다** 웹소켓 연결이 끊겼다가 **다시 연결**되도록 했으며,
이 때 **SEND** 작업이 진행되어 새로운 알림을 수신할 수 있도록 했습니다.

SEND 시 프로젝트 ID, 사용자 ID, 사용자 권한에 대한 정보가 서버로 전달되며,
해당 정보를 브로커에게 전달하여 작업들이 처리됩니다.

이후 처리 결과에 해당하는 미확인 알림건을 다시 서버 → 클라이언트 순으로 전달하여
관련 정보를 구독하고 있는 각각의 사용자에게 보여줍니다.

![alarm_student](https://github.com/ZINYED/choongang_project/assets/118190422/b39a22af-f3e7-421a-b0a5-f48c79aa07ae)

![alarm_leader](https://github.com/ZINYED/choongang_project/assets/118190422/1fb00bf7-cb3e-47a2-b2ad-17e8754c26f0)

![alarm_admin](https://github.com/ZINYED/choongang_project/assets/118190422/3bff404a-8579-4013-bac3-7b0f0f17c39d)

&nbsp;
# ⁉ 문제 및 해결
💡 **특정 이벤트가 발생될 때 마다 실시간 알림이 새롭게 갱신되지 않는 문제 발생**

### ❓ 원인 분석

- 특정 이벤트 발생 시 SEND 처리되는지 확인
    - WebSocket 연결 시에만 SEND 처리

### ❗ 해결 방법

- 단기간에 팀원들의 코드를 전체적으로 수정해야 하는 문제가 발생되어 5초 마다 WebSocket 연결이 끊겼다가 재연결되면서 SEND 처리되도록 수정하여 해결







