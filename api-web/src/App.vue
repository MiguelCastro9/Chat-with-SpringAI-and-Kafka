<script setup lang="ts">
import { ref, reactive, onMounted } from "vue";
import { ChatService } from "@/services/ChatService";
import { Chat } from "@/types/Chat";
import NavbarComponent from "@/components/NavbarComponent.vue";
import MarkdownIt from "markdown-it";
import { formatTime } from "@/utils-functions/formatTime";
const chatService = new ChatService();
const dataTechnologyMessage = ref<Array<Chat>>([]);
const loadingTechnologyMessage = ref(false);
const loadingSendTechnologyMessage = ref(false);
const dataBusinessMessage = ref<Array<Chat>>([]);
const loadingBusinessMessage = ref(false);
const loadingSendBusinessMessage = ref(false);
const showRegisterModal = ref(false);
const username = ref<string | null>(localStorage.getItem("username"));
const md = new MarkdownIt();

const paramTechnology = reactive({
  username: "",
  topic: "technology-topic",
  message: ""
});

const paramBusiness = reactive({
  username: "",
  topic: "business-topic",
  message: ""
});

const registerUser = () => {
  if ((username.value?.trim() ?? "") !== "") {
    localStorage.setItem("username", username.value?.trim() ?? "");
    paramTechnology.username = username.value?.trim() ?? "";
    paramBusiness.username = username.value?.trim() ?? "";
    showRegisterModal.value = false;
  }
};

const checkUser = () => {
  const username = localStorage.getItem("username");
  const gender = localStorage.getItem("gender");
  if (!username && !gender) {
    showRegisterModal.value = true;
  } else {
    paramTechnology.username = username ?? "";
    paramBusiness.username = username ?? "";
  }
};

const sendTechnologyMessage = async () => {
  loadingSendTechnologyMessage.value = true;
  checkUser();
  try {
    await chatService.sendTechnologyMessageService(paramTechnology);
    await loadTechnologyMessage();
    paramTechnology.message = "";
    loadingSendTechnologyMessage.value = false;
  } catch (error: any) {
    console.log(error);
    loadingSendTechnologyMessage.value = false;
  }
};

const sendBusinessMessage = async () => {
  loadingSendBusinessMessage.value = true;
  checkUser();
  try {
    await chatService.sendBusinessMessageService(paramBusiness);
    await loadBusinessMessage();
    paramBusiness.message = "";
    loadingSendBusinessMessage.value = false;
  } catch (error: any) {
    console.log(error);
    loadingSendBusinessMessage.value = false;
  }
};

const loadTechnologyMessage = async () => {
  loadingTechnologyMessage.value = true;
  checkUser();
  try {
    const data = await chatService.loadTechnologyMessageService();
    dataTechnologyMessage.value = data;
    loadingTechnologyMessage.value = false;
  } catch (error: any) {
    console.log(error);
    loadingTechnologyMessage.value = false;
  }
};

const loadBusinessMessage = async () => {
  loadingBusinessMessage.value = true;
  checkUser();
  try {
    const data = await chatService.loadBusinessMessageService();
    dataBusinessMessage.value = data;
    loadingBusinessMessage.value = false;
  } catch (error: any) {
    console.log(error);
    loadingBusinessMessage.value = false;
  }
};

function getInitials(name: string) {
  if (!name) return "";
  const names = name.trim().split(" ");
  return `${names[0][0]}${(names[names.length - 1] || names[0])[0]}`.toUpperCase();
}

function renderMarkdown(text: string) {
  return md.render(text);
}

onMounted(() => {
  checkUser();
  loadTechnologyMessage();
  loadBusinessMessage();
});

setInterval(() => {
  loadTechnologyMessage();
  loadBusinessMessage();
}, 1000);

</script>

<template>

  <!-- NAVBAR COMPONENT -->
  <NavbarComponent :username="username" />

  <n-modal
    v-model:show="showRegisterModal"
    preset="dialog"
    title="Register"
    :mask-closable="false"
    :closable="false"
    :close-on-esc="false"
  >
    <div class="modal-form">
      <n-input
        v-model:value="username"
        show-count
        clearable
        :maxlength="25"
        placeholder="Type your username..."
      />
      <n-button
        type="primary"
        style="margin-top: 12px"
        @click="registerUser"
        :disabled="!username"
        >Save</n-button
      >
    </div>
  </n-modal>

  <div class="container">
    <n-card title="Topics">
      <n-tabs type="segment" size="medium" animated>

        <!-- TECHNOLOGY TAB -->
        <n-tab-pane name="technology" tab="💻 Technology">
          <n-scrollbar style="max-height: 400px">
            <n-space vertical>
              <div
                v-for="(msg, index) in dataTechnologyMessage"
                :key="index"
                class="text-message"
              >
                <div class="avatar-message">{{ getInitials(msg.username) }}</div>
                <div class="content-message">
                  <div class="username-message">{{ msg.username }}</div>
                  <div v-html="renderMarkdown(msg.message)"></div>
                  <div v-if="msg.aianswer" v-html="renderMarkdown(`🤖 ${msg.aianswer}`)"></div>
                  <div class="time-message">{{ formatTime(msg.time) }}</div>
                </div>
              </div>
            </n-space>
          </n-scrollbar>
          <div class="input-message">
            <n-input
              v-model:value="paramTechnology.message"
              show-count
              clearable
              :maxlength="1000"
              placeholder="Type a message..."
              @keyup.enter="sendTechnologyMessage"
            />
            <n-button
              type="primary"
              @click="sendTechnologyMessage"
              :loading="loadingSendTechnologyMessage"
              :disabled="!paramTechnology.message"
              >Send</n-button
            >
          </div>
        </n-tab-pane>

        <!-- BUSINESS TAB -->
        <n-tab-pane name="business" tab="📊 Business">
          <n-scrollbar style="max-height: 400px">
            <n-space vertical>
              <div
                v-for="(msg, index) in dataBusinessMessage"
                :key="index"
                class="text-message"
              >
                <div class="avatar-message">{{ getInitials(msg.username) }}</div>
                <div class="content-message">
                  <div class="username-message">{{ msg.username }}</div>
                  <div v-html="renderMarkdown(msg.message)"></div>
                  <div v-if="msg.aianswer" v-html="renderMarkdown(`🤖 ${msg.aianswer}`)"></div>
                  <div class="time-message">{{ formatTime(msg.time) }}</div>
                </div>
              </div>
            </n-space>
          </n-scrollbar>
          <div class="input-message">
            <n-input
              v-model:value="paramBusiness.message"
              show-count
              clearable
              :maxlength="1000"
              placeholder="Type a message..."
              @keyup.enter="sendBusinessMessage"
            />
            <n-button
              type="primary"
              @click="sendBusinessMessage"
              :loading="loadingSendBusinessMessage"
              :disabled="!paramBusiness.message"
              >Send</n-button
            >
          </div>
        </n-tab-pane>

      </n-tabs>
    </n-card>
  </div>
</template>

<style scoped>
.container {
  max-width: 800px;
  margin: 40px auto;
}

.modal-form {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.input-message {
  display: flex;
  gap: 8px;
  margin-top: 12px;
}

.text-message {
  display: flex;
  align-items: flex-start;
  gap: 8px;
  margin-bottom: 10px;
}

.avatar-message {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  background-color: #18a058;
  width: 35px;
  height: 35px;
  border-radius: 50%;
  margin-right: 8px;
  flex-shrink: 0;
}

.content-message {
  background-color: #f0f0f5;
  padding: 6px 12px;
  border-radius: 14px;
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 100px;
  min-height: 60px;
}

.time-message {
  font-size: 10px;
  color: #888;
  margin-top: 4px;
  align-self: flex-end;
  margin-top: -12px;
}

.username-message {
  font-size: 12px;
  font-weight: bold;
}
</style>
