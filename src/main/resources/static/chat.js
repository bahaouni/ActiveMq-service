let stompClient = null;

// Function to connect to the WebSocket
function connect() {
    const socket = new SockJS('/chat'); // Connect to the WebSocket endpoint
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body)); // Handle incoming messages
        });
    });
}

// Function to send a message
function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();

    if (message && stompClient) {
        // Send the message to the /app/chat endpoint
        stompClient.send("/app/chat", {}, JSON.stringify({'message': message}));
        messageInput.value = ''; // Clear the input after sending
    }
}

// Function to display incoming messages
function showMessage(message) {
    const messagesDiv = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.innerText = message.message; // Display the message content
    messagesDiv.appendChild(messageElement);
    messagesDiv.scrollTop = messagesDiv.scrollHeight; // Scroll to the bottom
}

// Attach the sendMessage function to the button
document.getElementById('sendMessageButton').onclick = sendMessage;
