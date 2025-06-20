import http from "@/services/http";
import { Chat } from "@/types/Chat";

export class ChatService {

    public async sendTechnologyMessageService(params: object): Promise<Chat> {
        try {
            const res = await http.post('/chat/technology/send', params, {});
            return res.data;
        } catch (error: any) {
            console.log(error);
            throw error;
        }
    }

    public async loadTechnologyMessageService(): Promise<Array<Chat>> {
        try {
            const res = await http.get("/chat/technology/messages");
            return res.data;
        } catch (error: any) {
            console.log(error);
            throw error;
        }
    }

    public async sendBusinessMessageService(params: object): Promise<Chat> {
        try {
            const res = await http.post('/chat/business/send', params, {});
            return res.data;
        } catch (error: any) {
            console.log(error);
            throw error;
        }
    }

    public async loadBusinessMessageService(): Promise<Array<Chat>> {
        try {
            const res = await http.get("/chat/business/messages");
            return res.data;
        } catch (error: any) {
            console.log(error);
            throw error;
        }
    }
}
