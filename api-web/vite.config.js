import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path';
import { fileURLToPath } from 'url';
var __dirname = path.dirname(fileURLToPath(import.meta.url));
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    resolve: {
        alias: {
            '@': path.resolve(__dirname, 'src'),
            '@environments': path.resolve(__dirname, 'src/environments'),
            '@types': path.resolve(__dirname, 'src/types'),
            '@services': path.resolve(__dirname, 'src/services')
        }
    }
});
