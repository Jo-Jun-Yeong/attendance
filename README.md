
# 직원(Emp)							[/api/emp]
- 사원 등록
- 사원 전체 검색
- 사원 1명 검색(Long id)
- 사원 정보 업데이트(Long id, EmpUpdateRequest request)
- 사원 정보 삭제(Long id)

# 팀(Team)							[/api/team]
- 팀 등록(TeamCreateRequuest request)
- 팀 전체 검색
- 팀 1개 검색(Long id)
- 팀 정보(매니저)갱신 (TeamUpdateRequest request)
- 팀 삭제(Long id)

# 출/퇴근(Attendance)			[/api/attendance]
- 출근(Long employee_id) 
- 퇴근(Long employee_id) 
- 월간 근무시간 조회(Long employee_id, int month)

# 연차(DayOff)						[/api/dayoff]
- 연차 생성(직원생성시 Long id)
- 연차 갯수 조회(Long id)
- 모든 직원 연차 조회
- 연차 사용(UseDayOffRequest request) UseDayOffRequest (id, apllyDayte, reason)
- 연차 추가(Long idm int plusDayOff, String reason)
	
# 연차 기록(DayOffHistory)	[/api/dayoff]
- 모든 연차 이력 조회
- 모든 직원 월, 년 연차 이력 조회(int year, int month) 
- 직원1명 월,년 연차 이력 조회(Long id, int year, int month)
