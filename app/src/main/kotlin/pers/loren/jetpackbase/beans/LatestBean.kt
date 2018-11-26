package pers.loren.jetpackbase.beans

/**
 * Created by loren on 2018-11-26
 */
data class LatestBean(var node: Node,
                      var member: Member,
                      var last_reply_by: String,
                      var last_touched: Int,
                      var title: String,
                      var url: String,
                      var created: Int,
                      var content: String,
                      var content_rendered: String,
                      var last_modified: Int,
                      var replies: Int,
                      var id: Int) {
    data class Node(var avatar_large: String,
                    var name: String,
                    var avatar_normal: String,
                    var title: String,
                    var url: String,
                    var topics: Int,
                    var footer: String,
                    var header: String,
                    var title_alternative: String,
                    var avatar_mini: String,
                    var stars: Int,
                    var root: Boolean,
                    var id: Int,
                    var parent_node_name: String)

    data class Member(var username: String,
                      var website: String,
                      var github: String,
                      var psn: String,
                      var avatar_normal: String,
                      var bio: String,
                      var url: String,
                      var tagline: String,
                      var twitter: String,
                      var created: Int,
                      var avatar_large: String,
                      var avatar_mini: String,
                      var location: String,
                      var btc: String,
                      var id: Int)
}