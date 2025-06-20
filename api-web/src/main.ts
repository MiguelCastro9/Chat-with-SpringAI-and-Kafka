import { createApp } from 'vue';
import App from '@/App.vue';

import {
  create,
  NLayout,
  NLayoutSider,
  NSpace,
  NMenu,
  NIcon,
  NButton,
  NCard,
  NInput,
  NDropdown,
  NModal,
  NSpin,
  NBadge,
  NH4,
  NTabs,
  NTabPane,
  NScrollbar,
  NAvatar,
  NDivider
} from 'naive-ui';

const naive = create({
  components: [
    NLayout,
    NLayoutSider,
    NSpace,
    NMenu,
    NIcon,
    NButton,
    NCard,
    NInput,
    NDropdown,
    NModal,
    NSpin,
    NBadge,
    NH4,
    NTabs,
    NTabPane,
    NScrollbar,
    NAvatar,
    NDivider
  ]
});

const app = createApp(App);

app.use(naive);
app.mount('#app');
