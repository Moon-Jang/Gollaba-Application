import ApiTemplate from "./ApiTemplate"
import MethodType from "./MethodType"

const ApiGateway = {
    // Favorites Controller
    makeFavorite: async (payload, token) => ApiTemplate.sendApi(MethodType.POST, `v1/favorites`, payload, token),
    deleteFavorite: async (favoritesId, token) =>
        ApiTemplate.sendApi(MethodType.DELETE, `v1/favorites/${favoritesId}`, null, token),
    getFavorites: async (token) => ApiTemplate.sendApi(MethodType.GET, `v1/favorites`, null, token),
    // Poll Controller
    createPoll: async (payload, token) => ApiTemplate.sendApiMultiPart(MethodType.POST, "/v1/polls", payload, token),
    getPolls: async (offset, limit, token) =>
        ApiTemplate.sendApi(MethodType.GET, `/v1/polls?limit=${limit}&offset=${offset * 15}`, null, token),
    searchPolls: async (offset, limit, title, token) =>
        ApiTemplate.sendApi(
            MethodType.GET,
            `/v1/polls?limit=${limit}&offset=${offset * 15}&title=${title}`,
            null,
            token
        ),
    getPoll: async (pollId) => ApiTemplate.sendApi(MethodType.GET, `/v1/polls/${pollId}`),
    vote: async (pollId, payload) => ApiTemplate.sendApi(MethodType.POST, `/v1/polls/${pollId}/vote`, payload),
    updatePoll: async (pollId, payload, token) =>
        ApiTemplate.sendApi(MethodType.POST, `v1/polls/${pollId}/update`, payload, token),
    myPolls: async (token) => ApiTemplate.sendApi(MethodType.GET, `/v1/polls/me`, null, token),
    topPolls: async (token) => ApiTemplate.sendApi(MethodType.GET, `/v1/polls/top`, null, token),
    trendingPolls: async (token) => ApiTemplate.sendApi(MethodType.GET, `/v1/polls/trending`, null, token),
    // User Controllers
    signupForm: async (payload) => ApiTemplate.sendApi(MethodType.POST, `v2/users`, payload),
    showUser: async (userId, token) => ApiTemplate.sendApi(MethodType.GET, `/v1/users/${userId}`, null, token),
    updateForm: async (formData, token) =>
        ApiTemplate.sendApiMultiPart(MethodType.POST, `v1/users/update`, formData, token),
    readCount: async (pollId) => ApiTemplate.sendApiMultiPart(MethodType.POST, `/v1/polls/${pollId}/read-count`),
}

export default ApiGateway
