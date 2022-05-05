import axios from "axios"
import { MY_NOTES_URL } from "../BackendUrls";
import { GetNotePayload, PostNotePayload } from "./payloads";

export const getAllNotesRequest = async () => {
    const response = await axios.get(
        MY_NOTES_URL,
        {
            withCredentials: true
        }
    );
    return response;
}

export const postNoteRequest = async (notePayload: PostNotePayload) => {
    const response = await axios.post(
        MY_NOTES_URL,
        notePayload,
        {
            withCredentials: true
        }
    );
    return response.data;
}

export const deleteNoteRequest = async (noteId: number) => {
    const response = await axios.delete(
        MY_NOTES_URL + `/${noteId}`,
        {
            withCredentials: true
        }
    )
    return response.status;
}
