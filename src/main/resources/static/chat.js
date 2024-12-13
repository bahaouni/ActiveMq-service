const socket = new SockJS('http://localhost:8088/websocket');
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
    console.log('Connected to WebSocket');

    // Subscribe to notifications
    stompClient.subscribe('/topic/notifications', (notification) => {
        console.log('Received:', notification.body);
    });

    // Send a test message
    stompClient.send('/app/notify', {}, 'Hello, Admin!');
});
