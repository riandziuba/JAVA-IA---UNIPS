import { fetchWithToken } from "@/src/lib/fetchWithToken";
import { updateTag } from "next/cache";
import { cookies } from "next/headers";

export const handleCreateTask = async (_: string, formData: FormData) => {
    "use server";
    const task = formData.get("task")?.toString();

    if (!task) return "Você precisa informar o titulo da task";


    try {
        const body = {
            title: task
        }

        const cookieStore = await cookies();
        const token = cookieStore.get("token")?.value;

        const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks`, token!, {
            method: "POST",
            body: JSON.stringify(body)
        })

        if (message) return message;

        updateTag("get-tasks")
    } catch {
        console.error('handleRegister failed')

        return 'Erro no Criar task'
    }
}

export const handleCompleteTask = async (formData: FormData) => {
    "use server";
    const id = formData.get("id")?.toString();

    if (!id) {
        console.error("Você precisa informar o id da task");
        return;
    }

    try {

        const cookieStore = await cookies();
        const token = cookieStore.get("token")?.value;

        const completed = formData.get("completed") !== null;

        const endpoint = completed ? "complete" : "uncomplete";

        const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks/${id}/${endpoint}`, token!, {
            method: "PUT"
        })

        if (message) {
            console.error(message);
            return;
        }

        updateTag("get-tasks")
    } catch {
        console.error('handleCompleteTask failed')

        return;
    }
}

export const handleDeleteTask = async (formData: FormData) => {
    "use server";
    const id = formData.get("id")?.toString();

    if (!id) {
        console.error("Você precisa informar o id da task");
        return;
    }

    try {

        const cookieStore = await cookies();
        const token = cookieStore.get("token")?.value;

        const { message } = await fetchWithToken(`${process.env.BACKEND_URL}/tasks/${id}`, token!, {
            method: "DELETE"
        })

        if (message) {
            console.error(message);
            return;
        }

        updateTag("get-tasks")
    } catch {
        console.error('handleDeleteTask failed')

        return;
    }
}