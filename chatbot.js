const chatbotHTML = `
<div id="chatbot-widget" style="position:fixed;bottom:24px;right:24px;z-index:9999;font-family:'DM Sans',sans-serif">

  <!-- TOGGLE BUTTON -->
  <button id="chat-toggle" onclick="toggleChat()" style="
    width:56px;height:56px;border-radius:50%;background:#16a34a;
    border:none;cursor:pointer;box-shadow:0 4px 16px rgba(22,163,74,0.4);
    display:flex;align-items:center;justify-content:center;
    font-size:24px;transition:all .2s;margin-left:auto">
    💬
  </button>

  <!-- CHAT WINDOW -->
  <div id="chat-window" style="
    display:none;width:340px;height:480px;
    background:#fff;border-radius:16px;margin-bottom:12px;
    box-shadow:0 8px 32px rgba(0,0,0,0.15);
    border:1px solid #d1fae5;
    flex-direction:column;overflow:hidden">

    <!-- HEADER -->
    <div style="
      background:#16a34a;padding:16px 20px;
      display:flex;align-items:center;justify-content:space-between">
      <div>
        <div style="color:#fff;font-family:'Syne',sans-serif;
          font-weight:700;font-size:15px">EcoWaste AI Assistant</div>
        <div style="color:#bbf7d0;font-size:12px;margin-top:2px">
          Ask me anything about waste disposal
        </div>
      </div>
      <button onclick="toggleChat()" style="
        background:rgba(255,255,255,0.2);border:none;cursor:pointer;
        color:#fff;width:28px;height:28px;border-radius:50%;
        font-size:14px;display:flex;align-items:center;justify-content:center">
        ✕
      </button>
    </div>

    <!-- MESSAGES -->
    <div id="chat-messages" style="
      flex:1;overflow-y:auto;padding:16px;
      display:flex;flex-direction:column;gap:10px;
      background:#f8fffe;height:340px">

      <!-- Welcome message -->
      <div style="display:flex;gap:8px;align-items:flex-start">
        <div style="
          width:28px;height:28px;background:#16a34a;border-radius:50%;
          display:flex;align-items:center;justify-content:center;
          font-size:14px;flex-shrink:0">🌿</div>
        <div style="
          background:#fff;border:1px solid #d1fae5;border-radius:12px;
          border-bottom-left-radius:4px;padding:10px 14px;
          font-size:13px;color:#1a2e1d;max-width:80%;line-height:1.5">
          Hi! I am EcoWaste AI. Ask me anything about waste disposal in Hosur,
          Tamil Nadu — how to segregate, where to dispose, recycling tips, and more!
        </div>
      </div>

      <!-- Quick questions -->
      <div style="display:flex;flex-wrap:wrap;gap:6px;margin-top:4px">
        <button class="quick-q" onclick="askQuick('How do I dispose old batteries?')">
          Old batteries?
        </button>
        <button class="quick-q" onclick="askQuick('Where is the nearest e-waste centre in Hosur?')">
          E-waste centre?
        </button>
        <button class="quick-q" onclick="askQuick('How to compost wet waste at home?')">
          Compost at home?
        </button>
        <button class="quick-q" onclick="askQuick('What is hazardous waste?')">
          Hazardous waste?
        </button>
      </div>
    </div>

    <!-- INPUT -->
    <div style="
      padding:12px 16px;border-top:1px solid #d1fae5;
      background:#fff;display:flex;gap:8px">
      <input id="chat-input" type="text"
        placeholder="Ask about waste disposal..."
        style="
          flex:1;border:1.5px solid #d1fae5;border-radius:8px;
          padding:9px 12px;font-size:13px;outline:none;
          font-family:'DM Sans',sans-serif;color:#1a2e1d"
        onkeypress="if(event.key==='Enter') sendMessage()"
        onfocus="this.style.borderColor='#16a34a'"
        onblur="this.style.borderColor='#d1fae5'"/>
      <button onclick="sendMessage()" style="
        background:#16a34a;color:#fff;border:none;cursor:pointer;
        width:38px;height:38px;border-radius:8px;font-size:16px;
        display:flex;align-items:center;justify-content:center;
        transition:all .2s">
        ➤
      </button>
    </div>
  </div>
</div>

<style>
.quick-q {
  background:#f0fdf4;color:#16a34a;border:1px solid #d1fae5;
  border-radius:16px;padding:4px 10px;font-size:11px;cursor:pointer;
  font-family:'DM Sans',sans-serif;transition:all .2s;
}
.quick-q:hover { background:#16a34a;color:#fff; }
#chat-messages::-webkit-scrollbar { width:4px; }
#chat-messages::-webkit-scrollbar-thumb { background:#d1fae5;border-radius:2px; }
</style>
`;

document.body.insertAdjacentHTML('beforeend', chatbotHTML);

let chatOpen = false;

function toggleChat() {
  chatOpen = !chatOpen;
  const win = document.getElementById('chat-window');
  const btn = document.getElementById('chat-toggle');
  win.style.display = chatOpen ? 'flex' : 'none';
  win.style.flexDirection = 'column';
  btn.textContent = chatOpen ? '✕' : '💬';
  if (chatOpen) {
    document.getElementById('chat-input').focus();
  }
}

function askQuick(question) {
  document.getElementById('chat-input').value = question;
  sendMessage();
}

async function sendMessage() {
  const input = document.getElementById('chat-input');
  const message = input.value.trim();
  if (!message) return;
  input.value = '';

  addMessage(message, 'user');
  addTyping();

  try {
    const response = await fetch('http://localhost:8080/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message })
    });
    const data = await response.json();
    removeTyping();
    addMessage(data.reply, 'bot');
  } catch (e) {
    removeTyping();
    addMessage(getFallbackReply(message), 'bot');
  }
}

function addMessage(text, sender) {
  const messages = document.getElementById('chat-messages');
  const isBot = sender === 'bot';
  const div = document.createElement('div');
  div.style.cssText = `display:flex;gap:8px;align-items:flex-start;
    ${isBot ? '' : 'flex-direction:row-reverse'}`;
  div.innerHTML = `
    <div style="
      width:28px;height:28px;border-radius:50%;flex-shrink:0;
      background:${isBot ? '#16a34a' : '#e0f2fe'};
      display:flex;align-items:center;justify-content:center;font-size:13px">
      ${isBot ? '🌿' : '👤'}
    </div>
    <div style="
      background:${isBot ? '#fff' : '#16a34a'};
      color:${isBot ? '#1a2e1d' : '#fff'};
      border:${isBot ? '1px solid #d1fae5' : 'none'};
      border-radius:12px;
      ${isBot ? 'border-bottom-left-radius:4px' : 'border-bottom-right-radius:4px'};
      padding:10px 14px;font-size:13px;max-width:80%;line-height:1.5">
      ${text}
    </div>
  `;
  messages.appendChild(div);
  messages.scrollTop = messages.scrollHeight;
}

function addTyping() {
  const messages = document.getElementById('chat-messages');
  const div = document.createElement('div');
  div.id = 'typing-indicator';
  div.style.cssText = 'display:flex;gap:8px;align-items:flex-start';
  div.innerHTML = `
    <div style="width:28px;height:28px;background:#16a34a;border-radius:50%;
      display:flex;align-items:center;justify-content:center;font-size:13px">🌿</div>
    <div style="background:#fff;border:1px solid #d1fae5;border-radius:12px;
      border-bottom-left-radius:4px;padding:10px 14px;font-size:13px;color:#8aab90">
      Thinking...
    </div>
  `;
  messages.appendChild(div);
  messages.scrollTop = messages.scrollHeight;
}

function removeTyping() {
  const t = document.getElementById('typing-indicator');
  if (t) t.remove();
}

function getFallbackReply(msg) {
  const m = msg.toLowerCase();
  if (m.includes('battery') || m.includes('phone') || m.includes('laptop'))
    return 'Electronic items like batteries, phones, and laptops are E-Waste. Drop them at SIPCOT E-Waste Centre, Hosur. Never throw in regular bins as they contain toxic materials.';
  if (m.includes('plastic') || m.includes('paper') || m.includes('cardboard'))
    return 'Plastic, paper, and cardboard are Dry Waste. Clean them before disposal and drop at Hosur Dry Waste Collection Centre, SIPCOT Phase 1. Many can be recycled!';
  if (m.includes('food') || m.includes('vegetable') || m.includes('fruit') || m.includes('wet'))
    return 'Food and vegetable waste is Wet Waste. Best option is home composting. Otherwise drop at Hosur Municipality Compost Yard near Bus Stand. Keep separate from dry waste.';
  if (m.includes('paint') || m.includes('chemical') || m.includes('medicine') || m.includes('hazard'))
    return 'Paints, chemicals, and medicines are Hazardous Waste. Never pour down drains. Drop at Hosur Hazardous Waste Facility, Industrial Area. Contact 04344-456789.';
  if (m.includes('compost') || m.includes('home'))
    return 'Home composting is easy! Collect vegetable peels, fruit waste, and garden waste in a bin. Add dry leaves in layers. In 6-8 weeks you get rich compost for your garden. Great for Tamil Nadu homes!';
  if (m.includes('hosur') || m.includes('centre') || m.includes('location') || m.includes('where'))
    return 'Hosur disposal centres: E-Waste → SIPCOT Main Road. Dry Waste → SIPCOT Phase 1. Wet Waste → Near Bus Stand. Hazardous → Industrial Area. All open Mon-Sat. Use the Classify feature for exact details!';
  if (m.includes('hello') || m.includes('hi') || m.includes('help'))
    return 'Hello! I can help you with waste segregation, disposal locations in Hosur, recycling tips, and composting advice. What would you like to know?';
  return 'Great question! For waste disposal in Hosur, use our Classify feature to get instant AI-powered guidance. You can also ask me about specific waste types, composting, recycling, or disposal centre locations in Hosur!';
}
async function sendMessage() {
  const input = document.getElementById('chat-input');
  const message = input.value.trim();
  if (!message) return;
  input.value = '';

  addMessage(message, 'user');
  addTyping();

  try {
    const response = await fetch('http://localhost:8080/api/chat', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ message: message })
    });

    if (!response.ok) {
      throw new Error('Server error');
    }

    const data = await response.json();
    removeTyping();

    const reply = data.reply || data.message || data.answer;
    if (reply && reply !== 'undefined' && reply !== 'null') {
      addMessage(reply, 'bot');
    } else {
      addMessage(getFallbackReply(message), 'bot');
    }

  } catch (e) {
    removeTyping();
    addMessage(getFallbackReply(message), 'bot');
  }
}