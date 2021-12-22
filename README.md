# WebApp-Todo
 할 일을 기록하는 Todo 앱을 Web-App형태로 구현하였다.
 "Trello"라는 앱을 보고 모방하는 방식으로 만들었다고 생각해도 좋다

## 특징
- 서버-클라이언트
- 클라이언트에서는 서버에 저장된 TODO Table 정보를 요청한다
- 클라이언트에서 갱신된 TODO Table 정보에 대한 갱신을 요청한다
- 서버에서 Table 정보 요청 수신 시
 - 데이터베이스로부터 Table 정보 취득
 - 취득된 정보 이용해 웹페이지 구성
 - 구성된 웹페이지를 클라이언트에 응답
- 서버에서 Table 정보 갱신 요청 수신 시
 - 수신된 Table 정보를 데이터베이스에 저장
- 클라이언트는 수신된 Table 정보를 알맞게 출력한다
 - Todo, Doing, Done 상태가 있음
 - Todo Item 하나를 드래그 앤 드롭을 통해 옮기면서 상태를 변경 가능
  - 드래그 앤 드롭 관련 애니메이션
 - Todo Item 클릭 시 세부정보가 출력되며 필요하다면 수정 가능
  - 팝업창
 - "Todo Item 추가하기" 버튼을 이용해 새로운 Todo를 추가
  - 팝업창
 - 사용자가 조작을 통해 수정된 Table 정보는 서버로 보내져 갱신된다
- 한계점
 - Item 생성시간 데이터 생성, 변형 -> 불완전
 - 클라이언트 상에서 Item 삭제 불가
 - 데이터베이스의 Table 갱신 시 수정을 통해 갱신되는 것이 아니라, 기존 테이블을 삭제하고 새로운 테이블을 생성하여 갱신됨
  - 비효율적
 - 클라이언트의 Table에서 데이터 갱신 시 Table을 완전히 비우고 다시 그림
  - 
 - 클라이언트 상에서 데이터 저장, 접근 방식이 너무 번잡함
  - 중복된 데이터가 생성/저장
  - 그렇게 생긴 여러 데이터를 질서없이, 규칙없이, 일관되지 않게 접근, 수정, 삭제함
