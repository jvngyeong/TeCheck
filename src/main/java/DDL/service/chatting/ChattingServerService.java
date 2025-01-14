package DDL.service.chatting;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DDL.domain.ChattingParticipantDTO;
import DDL.domain.MessageDTO;
import DDL.mapper.AutoNumMapper;
import DDL.mapper.ChattingMapper;
import DDL.mapper.EmployeeMapper;
import DDL.mapper.MemberMapper;
import DDL.service.GetUserIdService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class ChattingServerService extends WebSocketServer {
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	ChattingMapper chattingMapper;
	
	@Autowired
	AutoNumMapper autoNumMapper;
	
	@Autowired
	GetUserIdService getUserIdService;
	
	private final Map<String, WebSocket> connections = new ConcurrentHashMap<>(); // 사용자 ID와 WebSocket을 맵핑
	List<MessageDTO> messageList = null;
	String roomNum = null;
	
    @Autowired
    public ChattingServerService(InetSocketAddress chattingAddress) {
        super(chattingAddress);
    }

    @PostConstruct
    public void startServer() {
        this.start();
        System.out.println("WebSocket server started successfully on " + this.getAddress());
    }
    
    @PreDestroy
    public void stopServer() {
    	if(this != null) {
    		try {
    			System.out.println("채팅 서버의 자원 및 포트를 정리했습니다.");
				this.stop();
			} catch (InterruptedException e) {
				System.out.println("채팅에서남");
				e.printStackTrace();
			}
    	}
    }
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
    	// URL에서 쿼리 파라미터 부분만 추출
    	String resource = handshake.getResourceDescriptor();  // /?userId=11234&roomNum=room_100001
    	System.out.println(resource);

    	String userId = null;

    	// 쿼리 파라미터 추출
    	if (resource.contains("?")) {
    	    String[] parts = resource.split("\\?");
    	    if (parts.length > 1) {
    	        String[] queryParams = parts[1].split("&");
    	        for (String param : queryParams) {
    	            String[] keyValue = param.split("=");
    	            if (keyValue.length == 2) {
    	                if ("userId".equals(keyValue[0])) {
    	                    userId = keyValue[1];  // userId 값 추출
    	                    System.out.println("userId = " + userId);
    	                } else if ("roomNum".equals(keyValue[0])) {
    	                    roomNum = keyValue[1];  // roomNum 값 추출
    	                    System.out.println("roomNum = " + roomNum);
    	                }
    	            }
    	        }
    	    }
    	}

        if (userId != null) {
        	//여기서 DB에 채팅방있는사람인지 체크
        	String memberNum = memberMapper.getMemberNum(userId);
        	String empNum = employeeMapper.getEmpNum(userId);
        	if(memberNum != null) {
        		List<ChattingParticipantDTO> chattingParticipantList = chattingMapper.getChattingParticipantInfo(memberNum);
        		if(chattingParticipantList.size() <= 0) {
        			roomNum = autoNumMapper.getAutoNum("room_", "6", "room_num", "chatting_room");
        			chattingMapper.createChattingRoom(roomNum);
        			chattingMapper.addUser(roomNum, memberNum);
        		}
        		else {
        			roomNum = chattingParticipantList.get(0).getRoomNum();
        			messageList = chattingMapper.getMessageList(roomNum);
        			for(MessageDTO messageDTO : messageList) {
        				if(messageDTO.getParticipantNum().equals(memberNum)) {
        					messageDTO.setUserId(userId);
        				}
        				else {
        					String otherId = getUserIdService.execute(messageDTO.getParticipantNum());
        					messageDTO.setUserId(otherId);
        				}
        			}
        			// 메시지 리스트를 JSON으로 직렬화
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        String messageListJson = objectMapper.writeValueAsString(messageList);

                        conn.send(messageListJson);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        		}
        	}
        	else {
        		List<ChattingParticipantDTO> chattingParticipantList = chattingMapper.getChattingParticipantInfo(empNum);
        		Boolean isIn = false;
        		for(ChattingParticipantDTO dto : chattingParticipantList) {
        			if(dto.getRoomNum().equals(roomNum)) {
        				isIn = true;
        			}
        		}
        		if(!isIn) {
        			chattingMapper.addUser(roomNum, empNum);
        		}
        		List<MessageDTO> messageList = chattingMapper.getMessageList(roomNum);
        		for(MessageDTO messageDTO : messageList) {
    				if(messageDTO.getParticipantNum().equals(empNum)) {
    					messageDTO.setUserId(userId);
    				}
    				else {
    					String otherId = getUserIdService.execute(messageDTO.getParticipantNum());
    					messageDTO.setUserId(otherId);
    				}
    			}
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String messageListJson = objectMapper.writeValueAsString(messageList);

                    conn.send(messageListJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }	
        	}
            connections.put(userId, conn);
            System.out.println("New connection: " + conn.getRemoteSocketAddress() + " with UserId: " + userId);
        } else {
            System.out.println("UserId is missing in the request.");
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        connections.remove(conn);
        System.out.println("Connection closed: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcastMessage(message);
        saveMessage(message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started.");
    }
    
    public void saveMessage(String message) {
    	String userId = message.substring(0, message.indexOf(":"));
    	String userNum = memberMapper.getMemberNum(userId);
    	if(userNum != null) {
    		List<ChattingParticipantDTO> chattingParticipantList = chattingMapper.getChattingParticipantInfo(userNum);
    		roomNum = chattingParticipantList.get(0).getRoomNum();
    	}
    	else {
    		userNum = employeeMapper.getEmpNum(userId);
    		List<ChattingParticipantDTO> chattingParticipantList = chattingMapper.getChattingParticipantInfo(userNum);
    	}
    	String messageNum = autoNumMapper.getAutoNum("message_", "9", "message_num", "message");
    	String receivedMessage = message.substring(message.indexOf(":") + 1);
        chattingMapper.saveMessage(messageNum, roomNum, userNum, receivedMessage);
    }

    public void broadcastMessage(String message) {
        for (Map.Entry<String, WebSocket> entry : connections.entrySet()) {
        	System.out.println(message);
            String userId = entry.getKey();
            WebSocket conn = entry.getValue();
            String returnUserId = message.substring(0, message.indexOf(":"));
            String returnMessage = message.substring(message.indexOf(":")+1);
            if(returnMessage == null) {
            	returnMessage = "";
            }
            if (isEmployee(userId) || userId.equals(returnUserId)) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setMessage(returnMessage);
                messageDTO.setMessageDate(new Date());
                messageDTO.setUserId(userId);

                try {
					ObjectMapper objectMapper = new ObjectMapper();
					String messageDTOJson = objectMapper.writeValueAsString(messageDTO);
					
					conn.send(messageDTOJson);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
                System.out.println("Message sent to userId: " + userId);
            }
        }
    }
    
    private boolean isEmployee(String userId) {
    	String empNum = employeeMapper.getEmpNum(userId);
    	if(empNum != null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
