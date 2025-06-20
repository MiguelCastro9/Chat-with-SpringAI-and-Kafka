<script setup lang="ts">
import { computed, h, Component } from "vue";
import { NDropdown, NIcon } from "naive-ui";
import { LogOutRound } from "@vicons/material";

const props = defineProps<{
  username: string | null
}>();

const avatarInitials = computed(() => {
  const fullName = props.username;
  if (!fullName) return "";
  const names = fullName.split(" ");
  return `${names[0][0]}${(names[names.length - 1] || names[0])[0]}`.toUpperCase();
});

const handleLogout = () => {
  localStorage.removeItem("username");
  localStorage.removeItem("gender");
};

function renderIcon(icon: Component) {
  return () => h(NIcon, null, { default: () => h(icon) });
}

const dropdownOptions = computed(() => [
  {
    label: "Sair",
    key: "logout",
    icon: renderIcon(LogOutRound)
  }
]);

function handleUserOptions(key: string) {
  if (key === "logout") handleLogout();
}
</script>

<template>
  <nav class="navbar">
    <div class="navbar-left">
        <span class="navbar-left-title"><strong>Ollama & Kafka</strong></span>
    </div>
    <div class="navbar-right">
      <n-dropdown
        trigger="click"
        size="medium"
        :options="dropdownOptions"
        @select="handleUserOptions"
        class="dropdown"
      >
        <div class="navbar-user">
          <div class="navbar-right-avatar">{{ avatarInitials }}</div>
          <div class="navbar-right-user-info">
            <div class="navbar-right-user-name">
              <strong>{{ props.username }}</strong>
            </div>
            <div class="navbar-right-user-role">
              Member
            </div>
          </div>
        </div>
      </n-dropdown>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  position: relative;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 60px;
  background-color: #fff;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
  padding: 0 20px;
}

.navbar-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.navbar-left-title {
  color: #020817;
  font-size: 20px;
  text-decoration: none;
}

.navbar-right {
  display: flex;
  align-items: center;
  gap: 10px;
}

.navbar-right-avatar {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  background-color: #18a058;
  width: 35px;
  height: 35px;
  border-radius: 50%;
  font-size: 14px;
  flex-shrink: 0;
}

.navbar-user {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;
}

.navbar-right-user-info {
  display: flex;
  flex-direction: column;
  line-height: 1.2;
}

.navbar-right-user-name {
  font-size: 14px;
  color: #020817;
}

.navbar-right-user-role {
  font-size: 12px;
  color: #444;
  text-align: left;
}
</style>
